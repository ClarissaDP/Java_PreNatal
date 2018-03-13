package controller;

import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import DAO.ControllerConsultaDAO;

public class ControllerConsulta {
	
		public Consulta cadastraNovaConsulta(String data_cons, boolean realizada, Paciente paciente) {
		
			Consulta consulta = new Consulta(data_cons, realizada);
			
			ControllerConsultaDAO consultaDAO = new ControllerConsultaDAO(); 
			consultaDAO.insereConsulta( consulta, paciente );
			
			return consulta;
		}
		
		
		
		public Consulta atualizaConsulta(String data_cons, boolean realizada, Usuario medico,
												String presc, String anam, String diag, String prog, Paciente paciente) {
			
			// Pesquisa consulta por data?
			ControllerConsultaDAO consultaDAO = new ControllerConsultaDAO(); 
			consultaDAO.atualizaConsulta(data_cons, realizada, medico, presc, anam, diag, prog, paciente);
			Consulta consulta = consultaDAO.buscaConsulta(data_cons, paciente);
			
			return consulta;
		}
	
		
		public static ArrayList<Consulta> listaConsultasAgend( Paciente paciente ) {
			ArrayList<Consulta> consultas = new ArrayList<Consulta>();
			
			ControllerConsultaDAO consultaDAO = new ControllerConsultaDAO();
			consultas = consultaDAO.listaConsultas(paciente, false);
			
			return consultas;
		}
		
		public static ArrayList<Consulta> listaConsultasReal( Paciente paciente ) {
			ArrayList<Consulta> consultas = new ArrayList<Consulta>();
			
			ControllerConsultaDAO consultaDAO = new ControllerConsultaDAO();
			consultas = consultaDAO.listaConsultas(paciente, true);
			
			return consultas;
		}
		
		public static void imprimeArrayListConsultas( ArrayList<Consulta> consultas ) {
			
			Iterator<Consulta> consultasIterator = consultas.iterator();
			while (consultasIterator.hasNext()) {
				Consulta aux = consultasIterator.next();
				if ( aux.getMedico() == null ) {
					System.out.println(aux.getData_cons() + ";" + aux.isRealizada() + ";" + aux.getMedico() + ";" + aux.getPresc()
										+ ";" + aux.getAnam() + ";" + aux.getDiag() + ";" + aux.getProg());
				}
				else {
					System.out.println(aux.getData_cons() + ";" + aux.isRealizada() + ";" + aux.getMedico().getCrm()	 + ";" + aux.getPresc()
							+ ";" + aux.getAnam() + ";" + aux.getDiag() + ";" + aux.getProg());
				}
			}
			
		}
		
}
