package view;

import java.awt.event.*;
import javax.swing.*;
import controller.ControllerUsuario;

public class JanelaCadastroUsuario extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private JTextField 	tfNome, tfCRM, tfLogin;
	private JPasswordField pfPass;
	private JButton BCadastra, BCancela;
	private JLabel 	Titulo, LInstrucoes, LNome, LCRM,
					LLogin, LPass;
	
	public JanelaCadastroUsuario(){
		//cria elementos da janela
		Titulo = new JLabel("Cadastrar Usuário", JTextField.LEFT);

		LInstrucoes = new JLabel("Digite os dados do novo usuário:", JTextField.CENTER);
		LNome = new JLabel("Nome do médico", JTextField.RIGHT);
		LCRM = new JLabel("CRM", JTextField.RIGHT);
		LLogin = new JLabel("Login", JTextField.RIGHT);
		LPass = new JLabel("Senha", JTextField.RIGHT);

		tfNome = new JTextField("");
		tfCRM = new JTextField("");
		tfLogin = new JTextField("");
		pfPass = new JPasswordField("");
		
		BCadastra = new JButton("Cadastrar");
		BCancela = new JButton("Cancelar");

		//define as propriedades dos elementos
		BCadastra.addActionListener(this);
		BCancela.addActionListener(this);
		tfNome.addActionListener(this);
		tfCRM.addActionListener(this);
		tfLogin.addActionListener(this);
		pfPass.addActionListener(this);

		Titulo.setBounds(10,5,180,20);
		LInstrucoes.setBounds(10,40,480,30);
		tfNome.setBounds(180,80,300,30);
		tfCRM.setBounds(180,120,300,30);
		tfLogin.setBounds(180,160,300,30);
		pfPass.setBounds(180,200,300,30);
		LNome.setBounds(10,80,160,30);
		LCRM.setBounds(10,120,160,30);
		LLogin.setBounds(10,160,160,30);
		LPass.setBounds(10,200,160,30);
		BCadastra.setBounds(75,240,130,30);
		BCancela.setBounds(295,240,130,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LInstrucoes);
		this.getContentPane().add(tfNome);
		this.getContentPane().add(tfCRM);
		this.getContentPane().add(tfLogin);
		this.getContentPane().add(pfPass);
		this.getContentPane().add(LNome);
		this.getContentPane().add(LCRM);
		this.getContentPane().add(LLogin);
		this.getContentPane().add(LPass);
		this.getContentPane().add(BCadastra);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(500,305);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else{
			String nome = tfNome.getText();
			String crm = tfCRM.getText();
			String login = tfLogin.getText();
			String senha = String.valueOf(pfPass.getPassword());

			ControllerUsuario new_user = new ControllerUsuario();
			new_user.cadastraNovoUsuario(nome, crm, login, senha);
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create() {
		JFrame janela = new JanelaCadastroUsuario();
		janela.setVisible(true);
		WindowListener x = new WindowAdapter (){
			public void windowClosing(WindowEvent e) {}
		};
		janela.addWindowListener(x);
	}
}
