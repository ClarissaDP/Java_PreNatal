package controller;
import DAO.ControllerUsuarioDAO;
import model.Usuario;


public class ControllerUsuario {

	public Usuario cadastraNovoUsuario(String nome, String crm, 
										String login, String senha) {
		
		Usuario usuario = new Usuario(nome, crm, login, senha);
		
		ControllerUsuarioDAO usuarioDAO = new ControllerUsuarioDAO(); 
		usuarioDAO.insereUsuario( usuario );
		
		return usuario;
	}
	
	public Usuario buscaUsuario(String login) {
		Usuario usuario;
		ControllerUsuarioDAO usuarioDAO = new ControllerUsuarioDAO(); 
		usuario = usuarioDAO.buscaUsuario( login );
		
		return usuario;
	}
 
	
	public boolean LoginUsuario( String login, String senha ) {
		boolean existe = false;
		
		ControllerUsuario new_user = new ControllerUsuario();
	    Usuario usuario = new_user.buscaUsuario(login);
	
		if ( usuario != null ) {
			if ( usuario.getSenha().equals(senha) ) {
				existe = true;
			}
		}
		
		return existe;
	}
	
	public static Usuario transformToUsuario ( String crm ) {
		
		Usuario usuario;
		ControllerUsuarioDAO usuarioDAO = new ControllerUsuarioDAO(); 
		usuario = usuarioDAO.buscaUsuarioCrm( crm );
		
		if ( usuario == null ) {
			System.out.println("Não achou. Sai daqui. Médico de Araque!");
		}
		
		return usuario;
	}
	

}

