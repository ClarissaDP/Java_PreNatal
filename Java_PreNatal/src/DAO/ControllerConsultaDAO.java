package DAO;

import java.io.*;
import java.util.ArrayList;

import model.*;

public class ControllerConsultaDAO {

	
	private static ArrayList<Consulta> leConsultas( Paciente paciente ) {
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/consultas/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Consulta aux;
					aux = (Consulta) in.readObject();
					consultas.add(aux);
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
		
		return consultas;
	}
	
	
	
	public void insereConsulta( Consulta consulta, Paciente paciente ) {
		// escrever dados de um Consulta para o arquivo
		try {
			// le dados antigos
			ArrayList<Consulta> consultas = new ArrayList<Consulta>();
			consultas = leConsultas(paciente);
			
			FileOutputStream arq = new FileOutputStream("database/consultas/"+paciente.getNumCad()+".dat");
			ObjectOutputStream out = new ObjectOutputStream(arq);
			
			// reescreve dados antigos
			boolean existe = false;
			for ( int i = 0; i < consultas.size() ; i++ ) {
				Consulta aux = consultas.get(i);
				// Não cadastra usuário se login ou crm não for único
				if ( aux.getData_cons().equals(consulta.getData_cons()) ) {
					existe = true;
				}
				out.writeObject(aux);
				out.flush();
			}
			
			// insere novo usuário abaixo dos antigos usuários
			if ( !existe ) {				
				Consulta aux = new Consulta(consulta.getData_cons(), consulta.isRealizada());
				out.writeObject(aux);
				out.flush();
				System.out.println("Consulta cadastrada com sucesso");
			}
			else {
				System.out.println("Paciente já possui consulta nessa data.");
			}
				
			out.close();	
		}
		catch( IOException exc ) {
			System.out.println("Erro ao gravar arquivo.");
		}
	}
	
	
	public static Consulta buscaConsulta( String data_cons, Paciente paciente ) {
		
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		consultas = leConsultas(paciente);
		
		Consulta consulta = null;
		
		for ( int i = 0; i < consultas.size() ; i++ ) {
			Consulta aux = consultas.get(i);
			if ( aux.getData_cons().equals(data_cons) ) {
				consulta = aux;
			}
		}
		
		return consulta;
	}
	
	
	public void atualizaConsulta( String data_cons, boolean realizada, Usuario medico,
									String presc, String anam, String diag, String prog, Paciente paciente ) {
		
		Consulta consulta = buscaConsulta(data_cons, paciente);
		if ( consulta == null ) {
			System.out.println("Não existe consulta agendada para essa data.");
		}
		else {
			try {
				// le dados antigos
				ArrayList<Consulta> consultas = new ArrayList<Consulta>();
				consultas = leConsultas(paciente);
				
				FileOutputStream arq = new FileOutputStream("database/consultas/"+paciente.getNumCad()+".dat");
				ObjectOutputStream out = new ObjectOutputStream(arq);
				
				// reescreve dados antigos
				boolean existe = false;
				for ( int i = 0; i < consultas.size() ; i++ ) {
					Consulta aux = consultas.get(i);
					if ( aux.getData_cons().equals(consulta.getData_cons()) ) {
						existe = true;
						aux.setRealizada(realizada);
						aux.setMedico(medico);
						aux.setPresc(presc);
						aux.setAnam(anam);
						aux.setDiag(diag);
						aux.setProg(prog);
						System.out.println("Atualização de consulta realizada com sucesso.");
					}
					out.writeObject(aux);
					out.flush();
				}
				
				// insere novo usuário abaixo dos antigos usuários
				if ( !existe ) {				
					System.out.println("Não pode atualizar consulta.");
				}
					
				out.close();	
			}
			catch( IOException exc ) {
				System.out.println("Erro ao gravar arquivo.");
			}
			
		}
	}
	
	
	
	
	public static void imprimeConsultas( Paciente paciente ) {
		
		// ler dados de arquivo
		try {
			FileInputStream arq = new FileInputStream("database/consultas/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			ArrayList<Consulta> consultas = new ArrayList<Consulta>();
			
			try {
			    for (int i = 0; ;i++) {
			    	Consulta aux;
					aux = (Consulta) in.readObject();
					if ( aux.getMedico() == null ) {
						System.out.println(i + "=" + aux.getData_cons() + ";" + aux.isRealizada() + ";" + aux.getMedico() + ";" + aux.getPresc()
											+ ";" + aux.getAnam() + ";" + aux.getDiag() + ";" + aux.getProg());
					}
					else {
						System.out.println(i + "=" + aux.getData_cons() + ";" + aux.isRealizada() + ";" + aux.getMedico().getCrm()	 + ";" + aux.getPresc()
								+ ";" + aux.getAnam() + ";" + aux.getDiag() + ";" + aux.getProg());
					}
					consultas.add(aux);
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
		catch ( NullPointerException ex ) {
			System.out.println("Arquivo ainda não existe");
		}
		
		
	}
	
	
	public ArrayList<Consulta> listaConsultas( Paciente paciente, boolean agend ) {
		ArrayList<Consulta> consultas = new ArrayList<Consulta>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/consultas/"+paciente.getNumCad()+".dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Consulta aux;
			    	aux = (Consulta) in.readObject();
			    	if ( aux.isRealizada() == agend ) {
						consultas.add(aux);
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
		
		return consultas;
	}
	
	
}
