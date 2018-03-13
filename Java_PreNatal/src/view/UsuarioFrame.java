package view;
import model.Usuario;
import controller.ControllerUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;


public class UsuarioFrame {

	public Usuario CadastraUsuarioFrame( ) {
			
		Scanner teclado = new Scanner(System.in);
		Usuario usuario = null;
		
		try {
		   System.out.println("Digite os dados do médico: "+"\nNome ");
		   String nome = teclado.nextLine();
		   System.out.println("CRM ");
		   String crm = teclado.nextLine();
		   System.out.println("Login ");
		   String login = teclado.nextLine();
		   System.out.println("Senha ");
		   String senha = teclado.nextLine();
				
		   ControllerUsuario new_user = new ControllerUsuario();
		   usuario = new_user.cadastraNovoUsuario(nome, crm, login, senha);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		   
	   teclado.close();
	   return usuario;
	}
	
	
	public Usuario BuscaUsuarioFrame( ) {
		
		Scanner teclado = new Scanner(System.in);
			
	     System.out.println("Login para pesquisar: ");
	     String login = teclado.nextLine();
	    
	     ControllerUsuario new_user = new ControllerUsuario();
	     Usuario usuario = new_user.buscaUsuario(login);
	
	     teclado.close();
		return usuario;
	}
	
	
	public void LoginUsuarioFrame() {		
		// "Frame"... Apenas teste no console
		
		Scanner teclado = new Scanner(System.in);
		
		 System.out.println("Login: ");
		 String login = teclado.nextLine();
		 System.out.println("Senha: ");
		 String senha = teclado.nextLine();
		
		 // testa se usuário e senha estão corretos
		 ControllerUsuario user = new ControllerUsuario();
		 boolean existe = user.LoginUsuario(login, senha);

		if ( !existe ) {
			System.out.println("Login ou senha incorreta.");
		}
		else {
			System.out.println("Logado com sucesso.");
		}
		
		teclado.close();
		
	}
			
}
