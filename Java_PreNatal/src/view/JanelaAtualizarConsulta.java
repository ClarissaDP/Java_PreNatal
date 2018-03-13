package view;

import java.awt.event.*;
import javax.swing.*;

import DAO.ControllerConsultaDAO;
import model.Usuario;
import model.Paciente;
import model.Consulta;
import controller.ControllerConsulta;


public class JanelaAtualizarConsulta extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private static 	Paciente paciente;
	private static JanelaPrincipal janelaprincipal;
	private static Consulta consulta;
	private JTextField 	tfData, tfMedico, tfPresc, tfAnam,
						tfDiag, tfProgn;
	private JButton BConfirma, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LData, LMedico, LPresc,LAnam, LDiag,
					LProgn;
	
	public JanelaAtualizarConsulta(){
		String d = JOptionPane.showInputDialog(this, "Digite a data da consulta:");
		consulta = ControllerConsultaDAO.buscaConsulta(d, paciente);
		if(consulta==null){
			JOptionPane.showMessageDialog(janelaprincipal, "Não existe consulta agendada para essa data.", "Erro", JOptionPane.ERROR_MESSAGE);
			janelaprincipal.setVisible(true);
			return;
		}

		//cria elementos da janela
		Titulo = new JLabel("Atualizar dados da consulta", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LData = new JLabel("Data", JTextField.RIGHT);
		LMedico = new JLabel("CRM do Médico", JTextField.RIGHT);
		LPresc = new JLabel("Prescrição", JTextField.RIGHT);
		LAnam = new JLabel("Anamnese", JTextField.RIGHT);
		LDiag = new JLabel("Diagnóstico", JTextField.RIGHT);
		LProgn = new JLabel("Prognóstico", JTextField.RIGHT);

		tfData = new JTextField(consulta.getData_cons());
		tfMedico = new JTextField(user.getCrm());
		tfPresc = new JTextField(consulta.getPresc());
		tfAnam = new JTextField(consulta.getAnam());
		tfDiag = new JTextField(consulta.getDiag());
		tfProgn = new JTextField(consulta.getProg());
		
		BConfirma = new JButton("Confirma");
		BCancela = new JButton("Cancela");

		//define as propriedades dos elementos
		BConfirma.addActionListener(this);
		BCancela.addActionListener(this);
		tfData.addActionListener(this);
		tfMedico.addActionListener(this);
		tfPresc.addActionListener(this);
		tfAnam.addActionListener(this);
		tfDiag.addActionListener(this);
		tfProgn.addActionListener(this);

		tfMedico.setEnabled(false);

		Titulo.setBounds(10,5,210,20);

		LUserNome.setBounds(190,5,270,20);
		LUserCRM.setBounds(460,5,130,20);

		LData.setBounds(10,30,210,30);
		LMedico.setBounds(10,70,210,30);
		LPresc.setBounds(10,110,210,30);
		LAnam.setBounds(10,150,210,30);
		LDiag.setBounds(10,190,210,30);
		LProgn.setBounds(10,230,210,30);

		tfData.setBounds(230,30,300,30);
		tfMedico.setBounds(230,70,300,30);
		tfPresc.setBounds(230,110,300,30);
		tfAnam.setBounds(230,150,300,30);
		tfDiag.setBounds(230,190,300,30);
		tfProgn.setBounds(230,230,300,30);

		BConfirma.setBounds(125,270,100,30);
		BCancela.setBounds(375,270,100,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LData);
		this.getContentPane().add(LMedico);
		this.getContentPane().add(LPresc);
		this.getContentPane().add(LAnam);
		this.getContentPane().add(LDiag);
		this.getContentPane().add(LProgn);

		this.getContentPane().add(tfData);
		this.getContentPane().add(tfMedico);
		this.getContentPane().add(tfPresc);
		this.getContentPane().add(tfAnam);
		this.getContentPane().add(tfDiag);
		this.getContentPane().add(tfProgn);

		this.getContentPane().add(BConfirma);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(600,335);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else{
			String data_cons = tfData.getText();
			String presc = tfPresc.getText();
			String anam = tfAnam.getText();
			String diag = tfDiag.getText();
			String prog = tfProgn.getText();
			boolean realizada = true;
			
			ControllerConsulta new_consulta = new ControllerConsulta();
			new_consulta.atualizaConsulta(data_cons, realizada, user, presc, anam, diag, prog, paciente);
			
			JOptionPane.showMessageDialog(null, "Dados da consulta atualizados.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		paciente = j.getPaciente();
		janelaprincipal=j;
		JFrame janela = new JanelaAtualizarConsulta();
		janela.setVisible(true);
		WindowListener x = new WindowAdapter (){
			public void windowClosing(WindowEvent e) {
				janelaprincipal.atualizaDados();
				janelaprincipal.setVisible(true);
			}
		};
		janela.addWindowListener(x);
	}
}
