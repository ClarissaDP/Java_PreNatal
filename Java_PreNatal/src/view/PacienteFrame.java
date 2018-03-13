package view;
import model.Paciente;
import controller.ControllerPaciente;

import java.util.*;


public class PacienteFrame {

	public Paciente CadastraPacienteFrame( ) {
			
		Scanner teclado = new Scanner(System.in);
		Paciente paciente = null;
		
		try {
			System.out.println("Digite os dados do paciente: "+"\nNome ");
			String nome = teclado.nextLine();
			System.out.println("Data de nascimento ");
			String dn = teclado.nextLine();
			System.out.println("Data da última menstruação ");
			String dum = teclado.nextLine();   
			System.out.println("Endereço ");
			String end = teclado.nextLine();
			System.out.println("Plano de Saúde ");
			String plano = teclado.nextLine();
			System.out.println("Data de Entrada ");
			String dataEnt = teclado.nextLine();
			System.out.println("Observações ");
			String observ = teclado.nextLine();
			System.out.println("Numero de cadastro "); // fazer um random para o número de cadastro?
			int numCad = teclado.nextInt();
			System.out.println("Telefone ");
			int tel = teclado.nextInt();
			System.out.println("CPF ");
			int cpf = teclado.nextInt();
			
			paciente = ControllerPaciente.cadastraNovoPaciente(nome, dn, tel, dum, cpf, end, 
																	plano, numCad, dataEnt, observ);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
			
	   //teclado.close();
	   return paciente;
	}
	
	
	public Paciente BuscaPacienteFrame( ) {
		
		Scanner teclado = new Scanner(System.in);
			
	     System.out.println("CPF para pesquisar: ");
	     int cpf = teclado.nextInt();
	    
	     ControllerPaciente new_paciente = new ControllerPaciente();
	     Paciente paciente = new_paciente.buscaPaciente(cpf);
	     
	     if ( paciente != null ) {
	    	 System.out.println("Paciente: " + paciente.getNome() + "; " + paciente.getCpf() + "; " + paciente.getNumCad() );
	     }
	     else {
	    	 System.out.println("Não existe nenhum paciente com esse CPF.");
	     }
	     
	     //teclado.close();
	     return paciente;
	}
	
			
}
