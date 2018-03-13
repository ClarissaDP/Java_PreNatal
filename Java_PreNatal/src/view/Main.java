package view;
//import controller.*;
import DAO.*;
//import model.*;

//import java.util.ArrayList;

// Utilizar Service ??? (criar interface) -> *Talvez valha mais pontos 
//	http://www.javabuilding.com/academy/patterns/service.html


//import java.io.*;

public class Main {
	

	public static void main( String args[] ) {

		// Login
		JanelaLogin.create(args);
		//Janela Principal
		
		// Usuarios
		//UsuarioFrame view_user = new UsuarioFrame();
		//Usuario usuario;
		
		ControllerUsuarioDAO.imprimeUsuarios();
		
		// Le dados novo paciente e cadastra
		//usuario = view_user.CadastraUsuarioFrame();
				
		
		// Pacientes
		//PacienteFrame view_paciente = new PacienteFrame();
		//Paciente paciente;
		
		ControllerPacienteDAO.imprimePacientes();
		
		// Le dados novo paciente e cadastra
		//paciente = view_paciente.CadastraPacienteFrame();
		
		// Busca paciente por cpf
		//paciente = view_paciente.BuscaPacienteFrame();
		//if ( paciente != null ) {
			
			
			// Consultas
			//ControllerConsultaDAO.imprimeConsultas(paciente);
			
			/*
			// marca a consulta (apenas data)
			ConsultaFrame view_consulta = new ConsultaFrame();
			Consulta consulta = view_consulta.CadastraConsultaFrame( paciente );
			
			// atualiza a consulta, através da data marcada
			ControllerConsultaDAO.imprimeConsultas(paciente);
			//consulta = view_consulta.AtualizaConsultaFrame( paciente );	
	

			System.out.println("Consultas agendadas:");
			ArrayList<Consulta> consultas = ControllerConsulta.listaConsultasAgend(paciente);
			ControllerConsulta.imprimeArrayListConsultas(consultas);
			
			System.out.println("Consultas realizadas:");
			consultas = ControllerConsulta.listaConsultasReal(paciente);
			ControllerConsulta.imprimeArrayListConsultas(consultas);
					
			*/

			
			// Exames
			
			//ControllerExameDAO.imprimeExames(paciente);
			//ExameFrame view_exame = new ExameFrame();
			
      // Cadastra novo exame
      //Exame exame = view_exame.CadastraExameFrame( paciente );
			
      // Atualiza/realiza exame (previamente agendado)
      //exame = view_exame.AtualizaExameFrame( paciente );
			
			// atualiza o exame, através da data marcada e nome
			//Falar para o André fazer uma "lista de opção de nome" para os nomes estarem identicos
		//	ControllerExameDAO.imprimeExames(paciente);

			//System.out.println("Exames agendados:");
			//ArrayList<Exame> exames = ControllerExame.listaExamesAgend(paciente);
			//ControllerExame.imprimeArrayListExames(exames);
			
			//System.out.println("Exames realizados:");
			//exames = ControllerExame.listaExamesReal(paciente);
			//ControllerExame.imprimeArrayListExames(exames);
			
		
			//exame = view_exame.AtualizaExameFrame( paciente );
			
			
			
			// Procedimentos
			//ControllerProcedimentoDAO.imprimeProcedimentos(paciente);
			//ProcedimentoFrame view_proced = new ProcedimentoFrame();

      // Cadastra novo procedimento
			//Procedimento proced = view_proced.CadastraProcedimentoFrame( paciente );
			
			//ControllerProcedimentoDAO.imprimeProcedimentos(paciente);
      
      // Atualiza/realiza procedimento (previamente agendado)
			//proced = view_proced.AtualizaProcedimentoFrame( paciente );
			
			//System.out.println("Procedimentos agendadas:");
			//ArrayList<Procedimento> procedz = ControllerProcedimento.listaProcedimentosAgend(paciente);
			//ControllerProcedimento.imprimeArrayListProcedimentos(procedz);
			
			//System.out.println("Procedimentos realizadas:");
			//procedz = ControllerProcedimento.listaProcedimentosReal(paciente);
			//ControllerProcedimento.imprimeArrayListProcedimentos(procedz);
			
		
		//}
				
	}

	
}
