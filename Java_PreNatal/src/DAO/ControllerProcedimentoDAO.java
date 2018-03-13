package DAO;
import model.*;

import java.io.*;
import java.util.ArrayList;


public class ControllerProcedimentoDAO {

	private static ArrayList<Procedimento> leProcedimentos( Paciente paciente ) {
		ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/procedimentos/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Procedimento aux;
					aux = (Procedimento) in.readObject();
					procedimentos.add(aux);
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
		
		return procedimentos;
	}
	
	
	public void insereProcedimento( Procedimento procedimento, Paciente paciente ) {
		// escrever dados de um Procedimento para o arquivo
		try {
			// le dados antigos
			ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
			procedimentos = leProcedimentos(paciente);
			
			FileOutputStream arq = new FileOutputStream("database/procedimentos/"+paciente.getNumCad()+".dat");
			ObjectOutputStream out = new ObjectOutputStream(arq);
			
			// reescreve dados antigos
			boolean existe = false;
			for ( int i = 0; i < procedimentos.size() ; i++ ) {
				Procedimento aux = procedimentos.get(i);
				if ( aux.getDate().equals(procedimento.getDate()) && aux.getNome().equals(procedimento.getNome()) ) {
					existe = true;
				}
				out.writeObject(aux);
				out.flush();
			}
			
			// insere novo usuário abaixo dos antigos usuários
			if ( !existe ) {				
				Procedimento aux = new Procedimento(procedimento.getNome(), procedimento.getDate(), procedimento.isRealizado());
				System.out.println(aux.getNome());
				out.writeObject(aux);
				out.flush();
				System.out.println("Procedimento cadastrada com sucesso");
			}
			else {
				System.out.println("Paciente já possui procedimento nessa data.");
			}
				
			out.close();	
		}
		catch( IOException exc ) {
			System.out.println("Erro ao gravar arquivo.");
		}
	}
	
	
	
	public static Procedimento buscaProcedimento( String nome, String data, Paciente paciente ) {
		
		ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
		procedimentos = leProcedimentos(paciente);
		
		Procedimento procedimento = null;
		
		for ( int i = 0; i < procedimentos.size() ; i++ ) {
			Procedimento aux = procedimentos.get(i);
			if ( aux.getDate().equals(data) && aux.getNome().equals(nome) ) {
				procedimento = aux;
			}
		}
		
		return procedimento;
	}
	
	
	
	public void atualizaProcedimento( String nome, String data, boolean realizada, 
									String laudo, Paciente paciente ) {

		Procedimento procedimento = buscaProcedimento(nome, data, paciente);
		if ( procedimento == null ) {
			System.out.println("Não existe procedimento '" + nome + "' agendado para essa data.");
		}
		else {
			try {
				// le dados antigos
				ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
				procedimentos = leProcedimentos(paciente);
				
				FileOutputStream arq = new FileOutputStream("database/procedimentos/"+paciente.getNumCad()+".dat");
				ObjectOutputStream out = new ObjectOutputStream(arq);
				
				// reescreve dados antigos
				boolean existe = false;
				for ( int i = 0; i < procedimentos.size() ; i++ ) {
					Procedimento aux = procedimentos.get(i);
					if ( aux.getDate().equals(procedimento.getDate()) && aux.getNome().equals(procedimento.getNome()) ) {
						existe = true;
						aux.setRealizado(realizada);
						aux.setLaudo(laudo);
						System.out.println("Atualização de procedimento realizada com sucesso.");
					}
					out.writeObject(aux);
					out.flush();
				}
				
				// insere novo usuário abaixo dos antigos usuários
				if ( !existe ) {				
					System.out.println("Não pode atualizar procedimento.");
				}
				
				out.close();	
			}
			catch( IOException exc ) {
				System.out.println("Erro ao gravar arquivo.");
			}
			
		}
	}
	
	
	
	public static void imprimeProcedimentos( Paciente paciente ) {
		
		// ler dados de arquivo
		try {
			FileInputStream arq = new FileInputStream("database/procedimentos/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
		
			ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
			
			try {
			    for (int i = 0; ; i++) {
			    	Procedimento aux;
					aux = (Procedimento) in.readObject();
					
					System.out.println(i + "=" + aux.getNome() + ";" + aux.isRealizado() + ";" + aux.getLaudo()
								+ ";" + aux.getDate());
					
					procedimentos.add(aux);
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
		catch ( NullPointerException ex ) {
			System.out.println("Arquivo ainda não existe");
		}
		
	}
	
	
	
	public ArrayList<Procedimento> listaProcedimentos( Paciente paciente, boolean agend ) {
		ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/procedimentos/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Procedimento aux;
			    	aux = (Procedimento) in.readObject();
			    	if ( aux.isRealizado() == agend ) {
						procedimentos.add(aux);
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
		
		return procedimentos;
	}


}
