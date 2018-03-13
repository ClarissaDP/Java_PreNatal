package view;

import java.awt.event.*;
import javax.swing.*;
import DAO.ControllerPacienteDAO;
import model.Usuario;
import model.Paciente;
import controller.ControllerPaciente;

public class JanelaCadastroPaciente extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private static JanelaPrincipal janelaprincipal;
	private JTextField 	tfNome, tfNasc, tfMenst, tfEnd,
						tfPlano, tfDataEntrada, tfObs,
						tfCadastro, tfTel, tfCPF;
	private JButton BConfirma, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LNome, LNasc, LMenst,LEnd, LPlano,
					LDataEntrada, LObs, LCadastro, LTel,
					LCPF;
	
	public JanelaCadastroPaciente(){
		//cria elementos da janela
		Titulo = new JLabel("Cadastro de Paciente", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LNome = new JLabel("Nome", JTextField.RIGHT);
		LNasc = new JLabel("Data de nascimento", JTextField.RIGHT);
		LMenst = new JLabel("Data da última mensturação", JTextField.RIGHT);
		LEnd = new JLabel("Endereço", JTextField.RIGHT);
		LPlano = new JLabel("Plano de saúde", JTextField.RIGHT);
		LDataEntrada = new JLabel("Data de entrada", JTextField.RIGHT);
		LObs = new JLabel("Observações", JTextField.RIGHT);
		LCadastro = new JLabel("Número de cadastro", JTextField.RIGHT);
		LTel = new JLabel("Telefone", JTextField.RIGHT);
		LCPF = new JLabel("CPF", JTextField.RIGHT);

		tfNome = new JTextField("");
		tfNasc = new JTextField("");
		tfMenst = new JTextField("");
		tfEnd = new JTextField("");
		tfPlano = new JTextField("");
		tfDataEntrada = new JTextField("");
		tfObs = new JTextField("");
		tfCadastro = new JTextField("");
		tfTel = new JTextField("");
		tfCPF = new JTextField("");
		
		BConfirma = new JButton("Confirma");
		BCancela = new JButton("Cancela");

		//define as propriedades dos elementos
		BConfirma.addActionListener(this);
		BCancela.addActionListener(this);
		tfNome.addActionListener(this);
		tfNasc.addActionListener(this);
		tfMenst.addActionListener(this);
		tfEnd.addActionListener(this);
		tfPlano.addActionListener(this);
		tfDataEntrada.addActionListener(this);
		tfObs.addActionListener(this);
		tfCadastro.addActionListener(this);
		tfTel.addActionListener(this);
		tfCPF.addActionListener(this);

		Titulo.setBounds(10,5,180,20);

		LUserNome.setBounds(190,5,270,20);
		LUserCRM.setBounds(460,5,130,20);

		LNome.setBounds(10,30,210,30);
		LNasc.setBounds(10,70,210,30);
		LMenst.setBounds(10,110,210,30);
		LEnd.setBounds(10,150,210,30);
		LPlano.setBounds(10,190,210,30);
		LDataEntrada.setBounds(10,230,210,30);
		LObs.setBounds(10,270,210,30);
		LCadastro.setBounds(10,310,210,30);
		LTel.setBounds(10,350,210,30);
		LCPF.setBounds(10,390,210,30);

		tfNome.setBounds(230,30,300,30);
		tfNasc.setBounds(230,70,300,30);
		tfMenst.setBounds(230,110,300,30);
		tfEnd.setBounds(230,150,300,30);
		tfPlano.setBounds(230,190,300,30);
		tfDataEntrada.setBounds(230,230,300,30);
		tfObs.setBounds(230,270,300,30);
		tfCadastro.setBounds(230,310,300,30);
		tfTel.setBounds(230,350,300,30);
		tfCPF.setBounds(230,390,300,30);

		BConfirma.setBounds(125,430,100,30);
		BCancela.setBounds(375,430,100,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LNome);
		this.getContentPane().add(LNasc);
		this.getContentPane().add(LMenst);
		this.getContentPane().add(LEnd);
		this.getContentPane().add(LPlano);
		this.getContentPane().add(LDataEntrada);
		this.getContentPane().add(LObs);
		this.getContentPane().add(LCadastro);
		this.getContentPane().add(LTel);
		this.getContentPane().add(LCPF);

		this.getContentPane().add(tfNome);
		this.getContentPane().add(tfNasc);
		this.getContentPane().add(tfMenst);
		this.getContentPane().add(tfEnd);
		this.getContentPane().add(tfPlano);
		this.getContentPane().add(tfDataEntrada);
		this.getContentPane().add(tfObs);
		this.getContentPane().add(tfCadastro);
		this.getContentPane().add(tfTel);
		this.getContentPane().add(tfCPF);

		this.getContentPane().add(BConfirma);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(600,500);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else{
			String nome = tfNome.getText();
			String dn = tfNasc.getText();
			String dum = tfMenst.getText();
			String end = tfEnd.getText();
			String plano = tfPlano.getText();
			String dataEnt = tfDataEntrada.getText();
			String observ = tfObs.getText();
			int numCad, tel, cpf;
			try{
				numCad = Integer.parseInt(tfCadastro.getText());
				tel = Integer.parseInt(tfTel.getText());
				cpf = Integer.parseInt(tfCPF.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null,
						"Os campos \"Número de cadastro\", \"telefone\" e \"CPF\" devem conter apenas números.",
						"Erro", JOptionPane.ERROR_MESSAGE);
			    return;
			}
			
			Paciente paciente=ControllerPaciente.cadastraNovoPaciente(nome, dn, tel, dum, cpf, end, plano, numCad, dataEnt, observ);
			janelaprincipal.setPaciente(paciente);
			ControllerPacienteDAO.imprimePacientes();
			JOptionPane.showMessageDialog(null, "Paciente "+nome+" cadastrada.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		janelaprincipal=j;
		JFrame janela = new JanelaCadastroPaciente();
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
