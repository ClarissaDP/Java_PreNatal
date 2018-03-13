package view;

import java.awt.event.*;
import javax.swing.*;
import model.Paciente;
import model.Usuario;
import controller.ControllerExame;

public class JanelaCadastroExame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private	static Paciente paciente;
	private static JanelaPrincipal janelaprincipal;
	private JTextField 	tfData, tfNomeExame;
	private JButton BCadastra, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LInstrucoes, LData, LNomeExame;
	
	public JanelaCadastroExame(){
		//cria elementos da janela
		Titulo = new JLabel("Cadastrar exame", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LInstrucoes = new JLabel("Digite os dados do exame:", JTextField.CENTER);
		LData = new JLabel("Data", JTextField.RIGHT);
		LNomeExame = new JLabel("Nome do exame", JTextField.RIGHT);

		tfData = new JTextField("");
		tfNomeExame = new JTextField("");
		
		BCadastra = new JButton("Cadastrar");
		BCancela = new JButton("Cancelar");

		//define as propriedades dos elementos
		BCadastra.addActionListener(this);
		BCancela.addActionListener(this);
		tfData.addActionListener(this);
		tfNomeExame.addActionListener(this);

		Titulo.setBounds(10,5,180,20);
		LUserNome.setBounds(90,5,270,20);
		LUserCRM.setBounds(360,5,130,20);
		LInstrucoes.setBounds(10,40,480,30);
		tfData.setBounds(180,80,300,30);
		tfNomeExame.setBounds(180,120,300,30);
		LData.setBounds(10,80,160,30);
		LNomeExame.setBounds(10,120,160,30);
		BCadastra.setBounds(75,160,130,30);
		BCancela.setBounds(295,160,130,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LInstrucoes);
		this.getContentPane().add(tfData);
		this.getContentPane().add(tfNomeExame);
		this.getContentPane().add(LData);
		this.getContentPane().add(LNomeExame);
		this.getContentPane().add(BCadastra);
		this.getContentPane().add(BCancela);

		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(500,220);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==BCancela){
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}else{
			String data_exame = tfData.getText();
			String nome_exame = tfNomeExame.getText();
			boolean realizada = false;

			ControllerExame new_exame = new ControllerExame();
			new_exame.cadastraExame(nome_exame, data_exame, realizada, " ", paciente);
			JOptionPane.showMessageDialog(null,	"Exame do paciente "+paciente.getNome()+" no dia "+data_exame+" cadastrado.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		paciente = j.getPaciente();
		janelaprincipal=j;
		JFrame janela = new JanelaCadastroExame();
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
