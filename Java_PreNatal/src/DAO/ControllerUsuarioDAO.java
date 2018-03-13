package DAO;
import model.Usuario;


import java.io.*;
import java.util.ArrayList;


public class ControllerUsuarioDAO {

	
	private static ArrayList<Usuario> leUsuarios( ) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		// ler dados de arquivo e adicionar no ArrayList
		try {
			FileInputStream arq = new FileInputStream("database/usuarios.dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			
			try {
			    for (;;) {
			    	Usuario aux;
					aux = (Usuario) in.readObject();
					usuarios.add(aux);
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
		
		return usuarios;
	}
		
	
	public void insereUsuario( Usuario usuario ) {
		// escrever dados de um Usuario para o arquivo
		try {
			// le dados antigos
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios = leUsuarios();
			
			FileOutputStream arq = new FileOutputStream("database/usuarios.dat");
			ObjectOutputStream out = new ObjectOutputStream(arq);
			
			// reescreve dados antigos
			boolean existe = false;
			for ( int i = 0; i < usuarios.size() ; i++ ) {
				Usuario aux = usuarios.get(i);
				// Não cadastra usuário se login ou crm não for único
				if ( aux.getLogin().equals(usuario.getLogin()) || aux.getCrm().equals(usuario.getCrm()) ) {
					existe = true;
				}
				out.writeObject(aux);
				out.flush();
			}
			
			// insere novo usuário abaixo dos antigos usuários
			if ( !existe ) {
				Usuario aux = new Usuario(usuario.getNome(), usuario.getCrm(), 
											usuario.getLogin(), usuario.getSenha());
				out.writeObject(aux);
				out.flush();
				System.out.println("Usuário cadastrado com sucesso");
			}
			else {
				System.out.println("Login ou CRM já cadastrada.");
			}
				
			out.close();	
		}
		catch( IOException exc ) {
			
			System.out.println("Erro ao gravar arquivo.");
		}
	}
	
	
	
	// Mudar para genérico <T> Callable?
	
	public Usuario buscaUsuario( String login ) {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = leUsuarios();
		
		Usuario usuario = null;
		
		for ( int i = 0; i < usuarios.size() ; i++ ) {
			Usuario aux = usuarios.get(i);
			if ( aux.getLogin().equals(login) ) {
				usuario = aux;
			}
		}
		
		return usuario;
	}
	
	public Usuario buscaUsuarioCrm( String crm ) {
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = leUsuarios();
		
		Usuario usuario = null;
		
		for ( int i = 0; i < usuarios.size() ; i++ ) {
			Usuario aux = usuarios.get(i);
			if ( aux.getCrm().equals(crm) ) {
				usuario = aux;
			}
		}
		
		return usuario;
	}
	
	
	public static void imprimeUsuarios( ) {
		
		// ler dados de arquivo
		try {
			FileInputStream arq = new FileInputStream("database/usuarios.dat");
			ObjectInputStream in = new ObjectInputStream(arq);
			int i = 0;
		
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			try {
			    for (;;) {
			    	Usuario aux;
					aux = (Usuario) in.readObject();
					System.out.println(i + "=" + aux.getNome() + ";" + aux.getCrm() + ";" 
										+ aux.getLogin() + ";" + aux.getSenha());
					usuarios.add(aux);
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
