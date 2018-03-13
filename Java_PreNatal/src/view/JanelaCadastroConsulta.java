package view;

import java.awt.event.*;
import javax.swing.*;
import model.Paciente;
import model.Usuario;
import controller.ControllerConsulta;

public class JanelaCadastroConsulta extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private	static Paciente paciente;
	private static JanelaPrincipal janelaprincipal;
	private JTextField 	tfData;
	private JButton BCadastra, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LData;
	
	public JanelaCadastroConsulta(){
		//cria elementos da janela
		Titulo = new JLabel("Cadastrar consulta", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LData = new JLabel("Digite uma data para cadastrar uma consulta para "+paciente.getNome()+":", JTextField.CENTER);

		tfData = new JTextField("");
		
		BCadastra = new JButton("Cadastrar");
		BCancela = new JButton("Cancelar");

		//define as propriedades dos elementos
		BCadastra.addActionListener(this);
		BCancela.addActionListener(this);
		tfData.addActionListener(this);

		Titulo.setBounds(10,5,180,20);
		LUserNome.setBounds(90,5,270,20);
		LUserCRM.setBounds(360,5,130,20);
		LData.setBounds(10,40,480,30);
		tfData.setBounds(105,80,300,30);
		BCadastra.setBounds(75,120,130,30);
		BCancela.setBounds(295,120,130,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LData);
		this.getContentPane().add(tfData);
		this.getContentPane().add(BCadastra);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(500,180);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BCadastra || e.getSource()==tfData){
			String data_cons = tfData.getText();
			boolean realizada = false;
			ControllerConsulta new_consulta = new ControllerConsulta();
			new_consulta.cadastraNovaConsulta(data_cons, realizada, paciente);
			JOptionPane.showMessageDialog(null,	"Consulta do paciente "+paciente.getNome()+" no dia "+data_cons+" cadastrada.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		paciente = j.getPaciente();
		janelaprincipal=j;
		JFrame janela = new JanelaCadastroConsulta();
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
