package view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Exame;
import model.Paciente;
import controller.ControllerExame;

public class ExameFrame {

	
public Exame CadastraExameFrame( Paciente paciente ) {
		
		Scanner teclado = new Scanner(System.in);
		Exame exame = null;
		
		System.out.println(paciente.getNome());
		
		try {
			System.out.println("Digite os dados do exame: ");
			System.out.println("Data do exame ");
			String data_exame = teclado.nextLine();	
			System.out.println("Digite o nome do exame: ");
			String nome_exame = teclado.nextLine();
			boolean realizada = false;
			
			ControllerExame new_exame = new ControllerExame();
			exame = new_exame.cadastraExame(nome_exame, data_exame, realizada, " ", paciente);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		catch ( NoSuchElementException ex ) { 
			System.out.println("No element exception " + ex);
		}
			
	   teclado.close();
	   return exame;
	}
	
	
public Exame AtualizaExameFrame( Paciente paciente ) {
		
		Scanner teclado = new Scanner(System.in);
		Exame exame = null;
		
		System.out.println(paciente.getNome());
		
		try {
			System.out.println("Digite os dados do exame: ");
			System.out.println("Nome do exame ");
			String nome_exame = teclado.nextLine();
			System.out.println("Data da exame ");
			String data_exame = teclado.nextLine();	
			System.out.println("Laudo ");
			String laudo = teclado.nextLine();
			boolean realizada = true;
			
			
			ControllerExame new_exame = new ControllerExame();
			exame = new_exame.atualizaExame(nome_exame, data_exame, realizada, laudo, paciente);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		catch ( NoSuchElementException ex ) { 
			System.out.println("No element exception " + ex);
		}
			
	   teclado.close();
	   return exame;
	}
	
}
