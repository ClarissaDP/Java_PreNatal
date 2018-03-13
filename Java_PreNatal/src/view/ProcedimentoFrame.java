package view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.*;
import controller.*;

public class ProcedimentoFrame {

	
public Procedimento CadastraProcedimentoFrame( Paciente paciente ) {
		
		Scanner teclado = new Scanner(System.in);
		Procedimento procedimento = null;
		
		try {
			System.out.println("Digite os dados do procedimento: ");
			System.out.println("Data do procedimento ");
			String data_procedimento = teclado.nextLine();	
			System.out.println("Digite o nome do procedimento: ");
			String nome_procedimento = teclado.nextLine();
			boolean realizada = false;
			
			ControllerProcedimento new_procedimento = new ControllerProcedimento();
			procedimento = new_procedimento.cadastraProcedimento(nome_procedimento, data_procedimento, realizada, paciente);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		catch ( NoSuchElementException ex ) { 
			System.out.println("No element exception " + ex);
		}
			
	   //teclado.close();
	   return procedimento;
	}
	
	
	public Procedimento AtualizaProcedimentoFrame( Paciente paciente ) {
		
		Scanner teclado = new Scanner(System.in);
		Procedimento procedimento = null;
		
		try {
			System.out.println("Digite os dados do procedimento: ");
			System.out.println("Data da procedimento ");
			String data_procedimento = teclado.nextLine();	
			System.out.println("Nome do procedimento ");
			String nome_procedimento = teclado.nextLine();
			System.out.println("Laudo ");
			String laudo = teclado.nextLine();
			boolean realizada = true;
			
			ControllerProcedimento new_procedimento = new ControllerProcedimento();
			procedimento = new_procedimento.atualizaProcedimento(nome_procedimento, data_procedimento, realizada, laudo, paciente);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		catch ( NoSuchElementException ex ) { 
			System.out.println("No element exception " + ex);
		}
			
	   //teclado.close();
	   return procedimento;
	}
	
}
