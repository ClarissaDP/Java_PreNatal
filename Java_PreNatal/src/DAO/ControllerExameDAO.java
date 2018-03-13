package DAO;
import model.*;

import java.io.*;
import java.util.ArrayList;

import model.Exame;
import model.Paciente;


public class ControllerExameDAO {

	private static ArrayList<Exame> leExames( Paciente paciente ) {
		ArrayList<Exame> exames = new ArrayList<Exame>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/exames/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Exame aux;
					aux = (Exame) in.readObject();
					exames.add(aux);
			    }
			}
			catch (EOFException exc) {
			    // end of stream
			}
			catch (IOException exc) {
			    // some other I/O error: print it, log it, etc.
			    exc.printStackTrace(); // for example
			}
				
			in.close();
		}
		catch ( IOException exc2 ) {
			System.out.println("Erro ao ler o arquivo." + exc2);			
		}
		catch ( ClassNotFoundException cnfex ) {
			System.out.println("Não achou a classe.");
		}
		
		return exames;
	}
	
	
	
	public void insereExame( Exame exame, Paciente paciente ) {
		// escrever dados de um exame para o arquivo
		try {
			// le dados antigos
			ArrayList<Exame> exames = new ArrayList<Exame>();
			exames = leExames(paciente);
			
			FileOutputStream arq = new FileOutputStream("database/exames/"+paciente.getNumCad()+".dat");
			ObjectOutputStream out = new ObjectOutputStream(arq);
			
			// reescreve dados antigos
			boolean existe = false;
			for ( int i = 0; i < exames.size() ; i++ ) {
				Exame aux = exames.get(i);
				if ( aux.getNome().equals(exame.getNome()) && aux.getDate().equals(exame.getDate()) ) {
					existe = true;
				}
				out.writeObject(aux);
				out.flush();
			}
			
			// insere novo usuário abaixo dos antigos usuários
			if ( !existe ) {				
				Exame aux = new Exame(exame.getNome(), exame.getDate(), exame.isRealizado(), " ");
				out.writeObject(aux);
				out.flush();
				System.out.println("Exame cadastrado com sucesso!");
			}
			else {
				System.out.println("Paciente já possui esse exame nessa data.");
			}
				
			out.close();	
		}
		catch( IOException exc ) {
			System.out.println("Erro ao gravar arquivo.");
		}
	}
	
	
	public static void imprimeExames( Paciente paciente ) {
		
		// ler dados de arquivo
		try {
			FileInputStream arq = new FileInputStream("database/exames/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			int i = 0;
		
			ArrayList<Exame> exames = new ArrayList<Exame>();
			
			try {
			    for (;; i++) {
			    	Exame aux;
					aux = (Exame) in.readObject();
					
					System.out.println(i + "=" + aux.getNome() + ";" + aux.isRealizado() + ";" + aux.getLaudo()
								+ ";" + aux.getDate());
					
					exames.add(aux);
			    }
			}
			catch (EOFException exc) {
			    // end of stream
			}
			catch (IOException exc) {
			    // some other I/O error: print it, log it, etc.
			    exc.printStackTrace(); // for example
			}
				
			in.close();
		}
		catch ( IOException exc2 ) {
			System.out.println("Erro ao ler o arquivo." + exc2);			
		}
		catch ( ClassNotFoundException cnfex ) {
			System.out.println("Não achou a classe.");
		}
	}



	public static Exame buscaExame(String nome, String data, Paciente paciente) {
		// TODO Auto-generated method stub
		
		ArrayList<Exame> exames = new ArrayList<Exame>();
		exames = leExames(paciente);
		
		Exame exame = null;
		
		for ( int i = 0; i < exames.size() ; i++ ) {
			Exame aux = exames.get(i);
			if ( aux.getDate().equals(data) && aux.getNome().equals(nome)) {
				exame = aux;
			}
		}
		
		return exame;

	}



	public void atualizaExame(String nome, String date, boolean realizado, String laudo, Paciente paciente) {
		
		Exame exame = buscaExame(nome, date, paciente);
		if ( exame == null ) {
			System.out.println("Não existe exame agendado para essa data.");
		}
		else {
			try {
				// le dados antigos
				ArrayList<Exame> exames = new ArrayList<Exame>();
				exames = leExames(paciente);
				
				FileOutputStream arq = new FileOutputStream("database/exames/"+paciente.getNumCad()+".dat");
				ObjectOutputStream out = new ObjectOutputStream(arq);
				
				// reescreve dados antigos
				boolean existe = false;
				for ( int i = 0; i < exames.size() ; i++ ) {
					Exame aux = exames.get(i);
					if ( aux.getNome().equals(exame.getNome()) && aux.getDate().equals(exame.getDate())) {
						existe = true;
						aux.setRealizado(realizado);
						aux.setLaudo(laudo);
						System.out.println("Atualização do exame realizada com sucesso.");
					}
					out.writeObject(aux);
					out.flush();
				}
				
				// insere novo usuário abaixo dos antigos usuários
				if ( !existe ) {				
					System.out.println("Não pode atualizar exame.");
				}
					
				out.close();	
			}
			catch( IOException exc ) {
				System.out.println("Erro ao gravar arquivo.");
			}
			
		}
		
		
		
	}

	public ArrayList<Exame> listaExame(Paciente paciente, boolean agend) {
		
		ArrayList<Exame> exames = new ArrayList<Exame>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/exames/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Exame aux;
			    	aux = (Exame) in.readObject();
			    	if ( aux.isRealizado() == agend ) {
						exames.add(aux);
			    	}
			    }
			}
			catch (EOFException exc) {
			    // end of stream
			}
			catch (IOException exc) {
			    // some other I/O error: print it, log it, etc.
			    exc.printStackTrace(); // for example
			}
				
			in.close();
		}
		catch ( IOException exc2 ) {
			System.out.println("Erro ao ler o arquivo.");			
		}
		catch ( ClassNotFoundException cnfex ) {
			System.out.println("Não achou a classe.");
		}
		
		return exames;
	}


}
