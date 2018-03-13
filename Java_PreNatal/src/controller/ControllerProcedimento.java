package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import DAO.ControllerProcedimentoDAO;
import DAO.ControllerProcedimentoDAO;


public class ControllerProcedimento {
	
	public Procedimento cadastraProcedimento(String nome, String date, boolean realizado, Paciente paciente){
		
		Procedimento procedimento = new Procedimento(nome, date, realizado);
		
		ControllerProcedimentoDAO procedimentoDAO = new ControllerProcedimentoDAO(); 
		procedimentoDAO.insereProcedimento( procedimento, paciente );
		
		return procedimento;	
	}
	
	public Procedimento atualizaProcedimento(String nome, String data, boolean realizado, 
												String laudo, Paciente paciente) {
		
		ControllerProcedimentoDAO procedimentoDAO = new ControllerProcedimentoDAO(); 
		procedimentoDAO.atualizaProcedimento(nome, data, realizado, laudo, paciente);
		Procedimento procedimento = procedimentoDAO.buscaProcedimento(nome, data, paciente);
		
		return procedimento;
	}
	
	
	
	public static ArrayList<Procedimento> listaProcedimentosAgend( Paciente paciente ) {
		ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
		
		ControllerProcedimentoDAO procedimentoDAO = new ControllerProcedimentoDAO();
		procedimentos = procedimentoDAO.listaProcedimentos(paciente, false);
		
		return procedimentos;
	}
	
	public static ArrayList<Procedimento> listaProcedimentosReal( Paciente paciente ) {
		ArrayList<Procedimento> procedimentos = new ArrayList<Procedimento>();
		
		ControllerProcedimentoDAO procedimentoDAO = new ControllerProcedimentoDAO();
		procedimentos = procedimentoDAO.listaProcedimentos(paciente, true);
		
		return procedimentos;
	}
	
	public static void imprimeArrayListProcedimentos( ArrayList<Procedimento> procedimentos ) {
		
		Iterator<Procedimento> procedimentosIterator = procedimentos.iterator();
		while (procedimentosIterator.hasNext()) {
			Procedimento aux = procedimentosIterator.next();
			System.out.println(aux.getNome() + ";" + aux.isRealizado() + ";" + aux.getLaudo()
					+ ";" + aux.getDate());
		}
		
	}
	
	
}
