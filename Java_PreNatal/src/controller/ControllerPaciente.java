package controller;
import model.*;

import java.io.*;
import java.util.*;

import DAO.ControllerPacienteDAO;


public class ControllerPaciente {
	
    	
	public static Paciente cadastraNovoPaciente(String nome, String dn, int tel, String dum, int cpf, String end, 
											String plano, int numCad, String dataEnt, String observ) {

    	Paciente paciente = new Paciente(nome, dn, tel, dum, cpf, end, 
											plano, numCad, dataEnt, observ);
		
		ControllerPacienteDAO pacienteDAO = new ControllerPacienteDAO(); 
		pacienteDAO.inserePaciente( paciente );
		
		return paciente;
	}

	
	public static Paciente buscaPaciente(int cpf) {
		Paciente paciente;
		ControllerPacienteDAO pacienteDAO = new ControllerPacienteDAO(); 
		paciente = pacienteDAO.buscaPaciente( cpf );
		
		return paciente;
	}
	

}
