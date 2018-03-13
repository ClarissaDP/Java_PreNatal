package DAO;
import model.Paciente;


import java.io.*;
import java.util.ArrayList;


public class ControllerPacienteDAO {

	
	private static ArrayList<Paciente> lePacientes( ) {
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/pacientes.dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Paciente aux;
					aux = (Paciente) in.readObject();
					pacientes.add(aux);
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
		
		return pacientes;
	}
		
	
	public void inserePaciente( Paciente paciente ) {
		// escrever dados de um Usuario para o arquivo
		try {
			// le dados antigos
			ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
			pacientes = lePacientes();
			
			FileOutputStream arq = new FileOutputStream("database/pacientes.dat");
			ObjectOutputStream out = new ObjectOutputStream(arq);
			
			// reescreve dados antigos
			boolean existe = false;
			for ( int i = 0; i < pacientes.size() ; i++ ) {
				Paciente aux = pacientes.get(i);
				// Não cadastra usuário se login ou crm não for único
				if ( aux.getCpf() == paciente.getCpf() || aux.getNumCad() == paciente.getNumCad() ) {
					existe = true;
				}
				out.writeObject(aux);
				out.flush();
			}
			
			// insere novo usuário abaixo dos antigos usuários
			if ( !existe ) {				
				Paciente aux = new Paciente(paciente.getNome(), paciente.getDN(), paciente.getTel(), 
												paciente.getDum(), paciente.getCpf(), paciente.getEnd(), 
												paciente.getPlano(), paciente.getNumCad(), paciente.getDataEnt(), paciente.getObserv());
				out.writeObject(aux);
				out.flush();
				System.out.println("Paciente cadastrado com sucesso");
			}
			else {
				System.out.println("CPF ou Número de cadastro já cadastrados.");
			}
				
			out.close();	
		}
		catch( IOException exc ) {
			System.out.println("Erro ao gravar arquivo.");
		}
	}
	
	
	
	public Paciente buscaPaciente( int cpf ) {
		
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		pacientes = lePacientes();
		
		Paciente paciente = null;
		
		for ( int i = 0; i < pacientes.size() ; i++ ) {
			Paciente aux = pacientes.get(i);
			if ( aux.getCpf() == cpf ) {
				paciente = aux;
			}
		}
		
		return paciente;
	}
	
	
	public static void imprimePacientes( ) {
		
		// ler dados de arquivo
		try {
			FileInputStream arq = new FileInputStream("database/pacientes.dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			int i = 0;
		
			ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
			
			try {
			    for (;;) {
			    	Paciente aux;
					aux = (Paciente) in.readObject();
					System.out.println(aux.getNome() + " - " + aux.getDN() + " - " 
							+ aux.getTel() + " - " + aux.getDum() + " - " + aux.getCpf() + " - " 
							+ aux.getEnd() + " - " + aux.getPlano() + " - " + aux.getNumCad() + " - " + aux.getDataEnt() + " - " + aux.getObserv());
					pacientes.add(aux);
					i++;
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
	}
		
	
}
