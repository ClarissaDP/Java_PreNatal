package view;

import java.awt.event.*;
import javax.swing.*;
import model.Paciente;
import model.Usuario;
import controller.ControllerProcedimento;

public class JanelaCadastroProcedimento extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private	static Paciente paciente;
	private static JanelaPrincipal janelaprincipal;
	private JTextField 	tfData, tfNomeProced;
	private JButton BCadastra, BCancela;
	private JLabel 	Titulo, LUserNome, LUserCRM,
					LInstrucoes, LData, LNomeProced;
	
	public JanelaCadastroProcedimento(){
		//cria elementos da janela
		Titulo = new JLabel("Cadastrar procedimento", JTextField.LEFT);
		LUserNome = new JLabel("Usuário :"+user.getNome(), JTextField.RIGHT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.RIGHT);

		LInstrucoes = new JLabel("Digite os dados do procedimento:", JTextField.CENTER);
		LData = new JLabel("Data", JTextField.RIGHT);
		LNomeProced = new JLabel("Nome do procedimento", JTextField.RIGHT);

		tfData = new JTextField("");
		tfNomeProced = new JTextField("");
		
		BCadastra = new JButton("Cadastrar");
		BCancela = new JButton("Cancelar");

		//define as propriedades dos elementos
		BCadastra.addActionListener(this);
		BCancela.addActionListener(this);
		tfData.addActionListener(this);
		tfNomeProced.addActionListener(this);

		Titulo.setBounds(10,5,180,20);
		LUserNome.setBounds(90,5,270,20);
		LUserCRM.setBounds(360,5,130,20);
		LInstrucoes.setBounds(10,40,480,30);
		tfData.setBounds(230,80,250,30);
		tfNomeProced.setBounds(230,120,250,30);
		LData.setBounds(10,80,210,30);
		LNomeProced.setBounds(10,120,210,30);
		BCadastra.setBounds(75,160,130,30);
		BCancela.setBounds(295,160,130,30);

		//insere os elementos na janela
		this.getContentPane().setLayout(null);
		this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LInstrucoes);
		this.getContentPane().add(tfData);
		this.getContentPane().add(tfNomeProced);
		this.getContentPane().add(LData);
		this.getContentPane().add(LNomeProced);
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
			String data_procedimento = tfData.getText();
			String nome_procedimento = tfNomeProced.getText();
			boolean realizada = false;

			ControllerProcedimento new_procedimento = new ControllerProcedimento();
			new_procedimento.cadastraProcedimento(nome_procedimento, data_procedimento, realizada, paciente);
			JOptionPane.showMessageDialog(null,	"Procedimento do paciente "+paciente.getNome()+" no dia "+data_procedimento+" cadastrado.");
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void create(JanelaPrincipal j) {
		user = j.getUsuario();
		paciente = j.getPaciente();
		janelaprincipal=j;
		JFrame janela = new JanelaCadastroProcedimento();
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
