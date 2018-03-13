package view;

import java.awt.event.*;
import javax.swing.*;

import DAO.ControllerPacienteDAO;
import model.Paciente;
import model.Usuario;

public class JanelaBuscaPaciente extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private	static Paciente paciente;
	private static JanelaPrincipal janelaprincipal;
	private JTextField 	tfCPF;
	private JButton BBusca, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LCPF;
	
	public JanelaBuscaPaciente(){
		//cria elementos da janela
		Titulo = new JLabel("Buscar de Paciente", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LCPF = new JLabel("Digite o CPF do paciente para buscar:", JTextField.CENTER);

		tfCPF = new JTextField("");
		
		BBusca = new JButton("Buscar");
		BCancela = new JButton("Cancela");

		//define as propriedades dos elementos
		BBusca.addActionListener(this);
		BCancela.addActionListener(this);
		tfCPF.addActionListener(this);

		Titulo.setBounds(10,5,180,20);
		LUserNome.setBounds(90,5,270,20);
		LUserCRM.setBounds(360,5,130,20);
		LCPF.setBounds(10,40,480,30);
		tfCPF.setBounds(105,80,300,30);
		BBusca.setBounds(90,120,100,30);
		BCancela.setBounds(310,120,100,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LCPF);
		this.getContentPane().add(tfCPF);
		this.getContentPane().add(BBusca);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(500,180);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BBusca || e.getSource()==tfCPF){
			int cpf;
			try{
				cpf = Integer.parseInt(tfCPF.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
			    return;
			}

			ControllerPacienteDAO pacienteDAO = new ControllerPacienteDAO(); 
			paciente = pacienteDAO.buscaPaciente( cpf );
			if(paciente != null){
				JOptionPane.showMessageDialog(null,"Paciente encontrado: "+paciente.getNome());
				janelaprincipal.setPaciente(paciente);
			}else{
				JOptionPane.showMessageDialog(null, "Não encontrado!");
				return;
			}
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		janelaprincipal=j;
		JFrame janela = new JanelaBuscaPaciente();
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
