package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import DAO.ControllerExameDAO;

public class ControllerExame {
	public Exame cadastraExame(String nome, String date, boolean realizado, String laudo, Paciente paciente){
		
		Exame exame = new Exame(nome, date, realizado, laudo);
		
		ControllerExameDAO exameDAO = new ControllerExameDAO(); 
		exameDAO.insereExame( exame, paciente );
		
		return exame;
		
	}
	
	public Exame atualizaExame(String nome, String date, boolean realizado, String laudo, Paciente paciente) {


		ControllerExameDAO exameDAO = new ControllerExameDAO(); 
		exameDAO.atualizaExame(nome, date, realizado, laudo, paciente);
		Exame exame = ControllerExameDAO.buscaExame(nome, date, paciente);

		return exame;
	}


	public static ArrayList<Exame> listaExamesAgend(Paciente paciente) {
		ArrayList<Exame> exames = new ArrayList<Exame>();

		ControllerExameDAO exameDAO = new ControllerExameDAO();
		exames = exameDAO.listaExame(paciente, false);

		return exames;
	}

	public static ArrayList<Exame> listaExamesReal( Paciente paciente ) {
		ArrayList<Exame> exames = new ArrayList<Exame>();
		
		ControllerExameDAO exameDAO = new ControllerExameDAO();
		exames = exameDAO.listaExame(paciente, true);
		
		return exames;
	}

	public static void imprimeArrayListExames( ArrayList<Exame> exames ) {

		Iterator<Exame> examesIterator = exames.iterator();
		while (examesIterator.hasNext()) {
			Exame aux = examesIterator.next();
			System.out.println(aux.getDate() + ";" + aux.isRealizado() + ";" + aux.getNome() + ";" + aux.getLaudo());
			
		}

	}
	
}
