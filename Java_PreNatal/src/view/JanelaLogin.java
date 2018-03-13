package view;

import java.awt.event.*;
import javax.swing.*;

import controller.ControllerUsuario;


public class JanelaLogin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private JButton B1,B2;
	private JLabel L1, L2, L3;
	private JTextField T1;
	private JPasswordField P1;
	
	
	public static void panelFrame( String frase ) {
        JOptionPane.showMessageDialog(null, frase);
    }
	
	
	public JanelaLogin() {
		//cria elementos da janela
		B1 = new JButton("Logar");
		B2 = new JButton("Cadastrar Novo Usuário");
		L1 = new JLabel("Login", JTextField.RIGHT);
		L2 = new JLabel("Senha", JTextField.RIGHT);
		L3 = new JLabel("Sistema de Acompanhamento Pré-Natal", JTextField.CENTER);
		T1 = new JTextField("");
		P1 = new JPasswordField("");

		//define as propriedades dos elementos
		B1.addActionListener(this);
		B2.addActionListener(this);		
		T1.addActionListener(this);
		P1.addActionListener(this);

		L3.setBounds(10,10,360,40);
		B1.setBounds(10,140,100,25);
		B2.setBounds(120,140,240,25);
		L1.setBounds(87,65,50,25);
		L2.setBounds(87,95,50,25);
		T1.setBounds(140,65,160,25);
		P1.setBounds(140,95,160,25);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(L3);
		this.getContentPane().add(L1);
		this.getContentPane().add(T1);
		this.getContentPane().add(L2);
		this.getContentPane().add(P1);
		this.getContentPane().add(B1);
		this.getContentPane().add(B2);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(380,200);
		this.setTitle("Login");
	}

	//aqui vai o código para tratar os eventos dos botôes
	public void actionPerformed(ActionEvent e) {
		
		// Botão de logar
		if (e.getSource() == B1 || e.getSource() == T1 || e.getSource() == P1) {			
			ControllerUsuario user = new ControllerUsuario();
			boolean existe = user.LoginUsuario(T1.getText(), String.valueOf(P1.getPassword()));// P1.getPassword());

			if ( !existe ) {
				String frase = "Login ou senha incorreta.";
				panelFrame(frase);
			}
			else {
				JanelaPrincipal.create(user.buscaUsuario(T1.getText()));
				this.setVisible(false);
			}
		}
		// Botão para cadastrar novo usuário
		if (e.getSource() == B2) {
			JanelaCadastroUsuario.create();
		}
	}
	
	public static void create( String args[] ) {
			JFrame janela = new JanelaLogin();
			//janela.show();
			janela.setVisible(true);
			WindowListener x = new WindowAdapter () {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			};
			janela.addWindowListener(x);
		}
	
}
