package view;

import java.awt.event.*;
import javax.swing.*;

import DAO.ControllerProcedimentoDAO;
import model.Usuario;
import model.Paciente;
import model.Procedimento;
import controller.ControllerProcedimento;

public class JanelaAtualizarProcedimento extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private static 	Paciente paciente;
	private static JanelaPrincipal janelaprincipal;
	private static Procedimento procedimento;
	private JTextField 	tfData, tfNomeProced, tfLaudo;
	private JButton BConfirma, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LData, LNomeProced, LLaudo;
	
	public JanelaAtualizarProcedimento(){
		JTextField nome_field = new JTextField(15);
		JTextField data_field = new JTextField(15);

		JPanel dialogPanel = new JPanel();
		dialogPanel.add(new JLabel("Nome do procedimento"));
		dialogPanel.add(nome_field);
		dialogPanel.add(Box.createHorizontalStrut(15));
		dialogPanel.add(new JLabel("Data do procedimento"));
		dialogPanel.add(data_field);

		JOptionPane.showConfirmDialog(null, dialogPanel, "Digite as informações do procedimento", JOptionPane.OK_CANCEL_OPTION);

		procedimento = ControllerProcedimentoDAO.buscaProcedimento(nome_field.getText(), data_field.getText(), paciente);
		if(procedimento==null){
			System.out.println("Não existe procedimento agendado para essa data.");
			JOptionPane.showMessageDialog(janelaprincipal, "Não existe consulta agendada para essa data.", "Erro", JOptionPane.ERROR_MESSAGE);
			janelaprincipal.setVisible(true);
			return;
		}

		//cria elementos da janela
		Titulo = new JLabel("Atualizar dados da consulta", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LData = new JLabel("Data", JTextField.RIGHT);
		LNomeProced = new JLabel("Nome do procedimento", JTextField.RIGHT);
		LLaudo = new JLabel("Laudo", JTextField.RIGHT);

		tfData = new JTextField(procedimento.getDate());
		tfNomeProced = new JTextField(procedimento.getNome());
		tfLaudo = new JTextField(procedimento.getLaudo());

		BConfirma = new JButton("Confirma");
		BCancela = new JButton("Cancela");

		//define as propriedades dos elementos
		BConfirma.addActionListener(this);
		BCancela.addActionListener(this);
		tfData.addActionListener(this);
		tfNomeProced.addActionListener(this);
		tfLaudo.addActionListener(this);

		Titulo.setBounds(10,5,210,20);

		LUserNome.setBounds(190,5,270,20);
		LUserCRM.setBounds(460,5,130,20);

		LData.setBounds(10,30,210,30);
		LNomeProced.setBounds(10,70,210,30);
		LLaudo.setBounds(10,110,210,30);

		tfData.setBounds(230,30,300,30);
		tfNomeProced.setBounds(230,70,300,30);
		tfLaudo.setBounds(230,110,300,30);

		BConfirma.setBounds(125,150,100,30);
		BCancela.setBounds(375,150,100,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LData);
		this.getContentPane().add(LNomeProced);
		this.getContentPane().add(LLaudo);

		this.getContentPane().add(tfData);
		this.getContentPane().add(tfNomeProced);
		this.getContentPane().add(tfLaudo);

		this.getContentPane().add(BConfirma);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(600,220);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else{
			String data_procedimento = tfData.getText();
			String nome_procedimento = tfNomeProced.getText();
			String laudo = tfLaudo.getText();
			boolean realizada = true;

			ControllerProcedimento new_procedimento = new ControllerProcedimento();
			new_procedimento.atualizaProcedimento(nome_procedimento, data_procedimento, realizada, laudo, paciente);

			JOptionPane.showMessageDialog(null, "Dados do procedimento atualizados.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		paciente = j.getPaciente();
		janelaprincipal=j;
		JFrame janela = new JanelaAtualizarProcedimento();
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
