package view;

import java.util.*;
import java.util.Scanner;

import model.*;
import controller.ControllerConsulta;
import controller.ControllerUsuario;

public class ConsultaFrame {

	
	
	public Consulta CadastraConsultaFrame( Paciente paciente ) {
		
		Scanner teclado = new Scanner(System.in);
		Consulta consulta = null;
		
		try {
			System.out.println("Digite os dados da consulta: ");
			System.out.println("Data da consulta ");
			String data_cons = teclado.nextLine();	
			boolean realizada = false;
			
			ControllerConsulta new_consulta = new ControllerConsulta();
			consulta = new_consulta.cadastraNovaConsulta(data_cons, realizada, paciente);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		catch ( NoSuchElementException ex ) { 
			System.out.println("No element exception " + ex);
		}
			
	   //teclado.close();
	   return consulta;
	}
	
	
public Consulta AtualizaConsultaFrame( Paciente paciente ) {
		
		Scanner teclado = new Scanner(System.in);
		Consulta consulta = null;
		
		System.out.println(paciente.getNome());
		
		try {
			System.out.println("Digite os dados da consulta: ");
			System.out.println("Data da consulta ");
			String data_cons = teclado.nextLine();	
			System.out.println("Médico CRM ");
			String medico = teclado.nextLine();
			System.out.println("Prescrição ");
			String presc = teclado.nextLine();
			System.out.println("Anamnese ");
			String anam = teclado.nextLine();
			System.out.println("Diagnóstico "); 
			String diag = teclado.nextLine();
			System.out.println("Prognóstico");
			String prog = teclado.nextLine();
			boolean realizada = true;
			
			// buscar medico / Usuario
			Usuario usuario = ControllerUsuario.transformToUsuario(medico);
			// current usuario logado?
			
			ControllerConsulta new_consulta = new ControllerConsulta();
			consulta = new_consulta.atualizaConsulta(data_cons, realizada, usuario, presc, 
															anam, diag, prog, paciente);
		}
		catch ( InputMismatchException ex ) { 
			System.out.println("Wrong type exception " + ex);
		}
		catch ( NoSuchElementException ex ) { 
			System.out.println("No element exception " + ex);
		}
			
	   //teclado.close();
	   return consulta;
	}
	
	
}
