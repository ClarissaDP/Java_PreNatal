package view;

import java.awt.event.*;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import model.*;
import controller.*;


public class JanelaPrincipal extends JFrame implements ActionListener {
	private static final long serialVersionUID = 0;
	private static 	Usuario user;
	private static Paciente paciente;
	private JButton BCadPaciente, BBuscaPaciente,
					BCadConsulta, BAtConsulta,
					BCadExame, BAtExame,
					BCadProc, BAtProc;
	private JTabbedPane abas;
	private JPanel abaConsultas, abaExames, abaProcedimentos;
	private JTable tabConsultas, tabExames, tabProcedimentos;
	private JScrollPane scrollConsultas, scrollExames, scrollProcedimentos;
	private JLabel 	Titulo, 
					LUserNome, LUserCRM,
					LPaciNome;
	private TableModelConsultas tmConsultas;
	private TableModelExamProc tmExames, tmProcedimentos;
	
	public JanelaPrincipal(){
		this.criaElementos();
		this.ajustaElementos();
		this.insereElementos();
		this.ajustaJanela();
		this.atualizaTabelas();
	}

	private void criaElementos(){
		//cria elementos da janela
		Titulo = new JLabel("Sistema de Acompanhamento Pré-Natal", JTextField.CENTER);
		LUserNome = new JLabel("Usuário: "+user.getNome(), JTextField.LEFT);
		LUserCRM = new JLabel("CRM: "+user.getCrm(), JTextField.LEFT);
		LPaciNome = new JLabel("<html>Cadastre ou busque um paciente para realizar uma ação</html>", JTextField.LEADING);
		
		BCadPaciente = new JButton("Cadastrar Paciente");
		BBuscaPaciente = new JButton("Buscar Paciente");
		BCadConsulta = new JButton("Cadastrar Consulta");
		BAtConsulta = new JButton("Atualizar Consulta");
		BCadExame = new JButton("Cadastrar Exame");
		BAtExame = new JButton("Atualizar Exame");
		BCadProc = new JButton("Cadastrar Procedimento");
		BAtProc = new JButton("Atualizar Procedimento");

		abas = new JTabbedPane();
		abaConsultas = new JPanel(false);
		abaExames = new JPanel(false);
		abaProcedimentos = new JPanel(false);

		tmConsultas = new TableModelConsultas();
		tmExames = new TableModelExamProc();
		tmProcedimentos = new TableModelExamProc();

		tabConsultas = new JTable(tmConsultas);
		scrollConsultas = new JScrollPane(tabConsultas);
		tabExames = new JTable(tmExames);
		scrollExames = new JScrollPane(tabExames);
		tabProcedimentos = new JTable(tmProcedimentos);
		scrollProcedimentos = new JScrollPane(tabProcedimentos);
	}

	private void ajustaElementos(){
		//define as propriedades dos elementos
		BCadPaciente.addActionListener(this);
		BBuscaPaciente.addActionListener(this);
		BCadConsulta.addActionListener(this);
		BAtConsulta.addActionListener(this);
		BCadExame.addActionListener(this);
		BAtExame.addActionListener(this);
		BCadProc.addActionListener(this);
		BAtProc.addActionListener(this);

		BCadConsulta.setEnabled(false);
		BAtConsulta.setEnabled(false);
		BCadExame.setEnabled(false);
		BAtExame.setEnabled(false);
		BCadProc.setEnabled(false);
		BAtProc.setEnabled(false);

		Titulo.setBounds(10,5,380,20);

		LUserNome.setBounds(90,5,270,20);
		LUserCRM.setBounds(260,5,130,20);
		LPaciNome.setBounds(250,30,170,60);
		
		BCadPaciente.setBounds(10,30,210,30);
		BBuscaPaciente.setBounds(10,70,210,30);

		abas.setBounds(10,110,480,250);
		abaConsultas.setBounds(0,0,480,250);
		abaExames.setBounds(0,0,480,250);
		abaProcedimentos.setBounds(0,0,480,250);

		tabConsultas.setPreferredScrollableViewportSize(new Dimension(465, 175));
		tabConsultas.setFillsViewportHeight(true);
		tabExames.setPreferredScrollableViewportSize(new Dimension(465, 175));
		tabExames.setFillsViewportHeight(true);
		tabProcedimentos.setPreferredScrollableViewportSize(new Dimension(465, 175));
		tabProcedimentos.setFillsViewportHeight(true);

		scrollConsultas.setBounds(5,5,470,180);
		scrollExames.setBounds(5,5,470,180);
		scrollProcedimentos.setBounds(5,5,470,180);

		BCadConsulta.setBounds(10,190,210,30);
		BAtConsulta.setBounds(230,190,210,30);
		BCadExame.setBounds(10,190,210,30);
		BAtExame.setBounds(230,190,210,30);
		BCadProc.setBounds(10,190,210,30);
		BAtProc.setBounds(230,190,210,30);

	}

	private void insereElementos(){
		//insere os elementos na janela

		abaConsultas.setLayout(null);
		abaConsultas.add(scrollConsultas);
		abaConsultas.add(BCadConsulta);
		abaConsultas.add(BAtConsulta);
		abaExames.setLayout(null);
		abaExames.add(scrollExames);
		abaExames.add(BCadExame);
		abaExames.add(BAtExame);
		abaProcedimentos.setLayout(null);
		abaProcedimentos.add(scrollProcedimentos);
		abaProcedimentos.add(BCadProc);
		abaProcedimentos.add(BAtProc);

		abas.addTab("Consultas", abaConsultas);
		abas.addTab("Exames", abaExames);
		abas.addTab("Procedimentos", abaProcedimentos);

		this.getContentPane().setLayout(null);
		//this.getContentPane().add(Titulo);
		this.getContentPane().add(LUserNome);
		this.getContentPane().add(LUserCRM);
		this.getContentPane().add(LPaciNome);
		this.getContentPane().add(BCadPaciente);
		this.getContentPane().add(BBuscaPaciente);
		this.getContentPane().add(abas);
	}

	public void ajustaJanela(){
		//ajusta propriedades da janela
		this.setLocation(200,200);
		this.setSize(500,395);
		this.setTitle("Sistema de Acompanhamento Pré Natal");
	}

	public void atualizaDados(){
		atualizaTabelas();
		if(paciente!=null){
			LPaciNome.setText("<html><h3>Paciente:</h3><p>"+paciente.getNome()+"</p></html>");
			BCadConsulta.setEnabled(true);
			BAtConsulta.setEnabled(true);
			BCadExame.setEnabled(true);
			BAtExame.setEnabled(true);
			BCadProc.setEnabled(true);
			BAtProc.setEnabled(true);
		}else{
			LPaciNome.setText("<html>Cadastre ou busque um paciente para realizar uma ação</html>");
			BCadConsulta.setEnabled(false);
			BAtConsulta.setEnabled(false);
			BCadExame.setEnabled(false);
			BAtExame.setEnabled(false);
			BCadProc.setEnabled(false);
			BAtProc.setEnabled(false);
		}
	}

	private void atualizaTabelas(){
		if(paciente!=null){
			ArrayList<Consulta> consultas = ControllerConsulta.listaConsultasAgend(paciente);
			Consulta consulta;
			String data_cons, realizada, medico, presc, anam, diag, prog;
			for(int i=0; i<consultas.size(); ++i){
				consulta = consultas.get(i);
				if(consulta.getData_cons()!=null)
					data_cons = consulta.getData_cons();
				else
					data_cons = "";
				if(consulta.isRealizada())
					realizada = "sim";
				else
					realizada = "não";
				if(consulta.getMedico()!=null && consulta.getMedico().getCrm()!=null)
					medico = consulta.getMedico().getCrm();
				else
					medico = "";
				if(consulta.getPresc()!=null)
					presc = consulta.getPresc();
				else
					presc = "";
				if(consulta.getAnam()!=null)
					anam = consulta.getAnam();
				else
					anam = "";
				if(consulta.getDiag()!=null)
					diag = consulta.getDiag();
				else
					diag = "";
				if(consulta.getProg()!=null)
					prog = consulta.getProg();
				else
					prog = "";
				tmConsultas.setValueAt(data_cons, i, 0);
				tmConsultas.setValueAt(realizada, i, 1);
				tmConsultas.setValueAt(medico, i, 2);
				tmConsultas.setValueAt(presc, i, 3);
				tmConsultas.setValueAt(anam, i, 4);
				tmConsultas.setValueAt(diag, i, 5);
				tmConsultas.setValueAt(prog, i, 6);
			}

			ArrayList<Exame> exames = ControllerExame.listaExamesAgend(paciente);
			if(exames!=null){
				Exame exame;
				String data_exam, nome_exam, laudo;
				for(int i=0; i<exames.size(); ++i){
					exame = exames.get(i);
					if(exame.getDate()!=null)
						data_exam = exame.getDate();
					else
						data_exam = "";
					if(exame.isRealizado())
						realizada = "sim";
					else
						realizada = "não";
					if(exame.getNome()!=null)
						nome_exam = exame.getNome();
					else
						nome_exam = "";
					if(exame.getLaudo()!=null)
						laudo = exame.getLaudo();
					else
						laudo = "";
					tmExames.setValueAt(data_exam, i, 0);
					tmExames.setValueAt(nome_exam, i, 1);
					tmExames.setValueAt(realizada, i, 2);
					tmExames.setValueAt(laudo, i, 3);
				}
			}
			ArrayList<Procedimento> procedimentos = ControllerProcedimento.listaProcedimentosAgend(paciente);
			if(procedimentos!=null){
				Procedimento procedimento;
				String data_proc, nome_proc,laudo;
				for(int i=0; i<procedimentos.size(); ++i){
					procedimento = procedimentos.get(i);
					if(procedimento.getDate()!=null)
						data_proc = procedimento.getDate();
					else
						data_proc = "";
					if(procedimento.isRealizado())
						realizada = "sim";
					else
						realizada = "não";
					if(procedimento.getNome()!=null)
						nome_proc = procedimento.getNome();
					else
						nome_proc = "";
					if(procedimento.getLaudo()!=null)
						laudo = procedimento.getLaudo();
					else
						laudo = "";
					tmProcedimentos.setValueAt(data_proc, i, 0);
					tmProcedimentos.setValueAt(nome_proc, i, 1);
					tmProcedimentos.setValueAt(realizada, i, 2);
					tmProcedimentos.setValueAt(laudo, i, 3);
				}
			}
		}
	}

	public void setPaciente(Paciente p){
		paciente = p;
	}

	public Paciente getPaciente(){
		return paciente;
	}

	public void setUsuario(Usuario u){
		user = u;
	}

	public Usuario getUsuario(){
		return user;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==BCadPaciente){
			this.setVisible(false);
			JanelaCadastroPaciente.create(this);
		}else if(e.getSource()==BBuscaPaciente){
			this.setVisible(false);
			JanelaBuscaPaciente.create(this);
		}else if(e.getSource()==BCadConsulta){
			this.setVisible(false);
			JanelaCadastroConsulta.create(this);
		}else if(e.getSource()==BAtConsulta){
			this.setVisible(false);
			JanelaAtualizarConsulta.create(this);
		}else if(e.getSource()==BCadExame){
			this.setVisible(false);
			JanelaCadastroExame.create(this);
		}else if(e.getSource()==BAtExame){
			this.setVisible(false);
			JanelaAtualizarExame.create(this);
		}else if(e.getSource()==BCadProc){
			JanelaCadastroProcedimento.create(this);
		}else if(e.getSource()==BAtProc){
			JanelaAtualizarProcedimento.create(this);
		}
	}

	public static void create(Usuario u) {
		user = u;
		paciente=null;
		JFrame janela = new JanelaPrincipal();
		janela.setVisible(true);
		WindowListener x = new WindowAdapter (){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		janela.addWindowListener(x);
	}
	
	class TableModelConsultas extends AbstractTableModel {
		private static final long serialVersionUID = 0;
        private String[] nomesColunas = {"Data",
                                        "Realizada",
                                        "Médico",
                                        "Prescrição",
                                        "Anamnese",
                                        "Diagnóstico",
                                        "Prognóstico"
                                        };
       private Object[][] data = {
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   {"","","","","","",""},
    		   
    		   {"","","","","","",""}};
 
        public int getColumnCount() {
            return nomesColunas.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return nomesColunas[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class<?> getColumnClass(int c) {
        	return String.class;
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
                return false;
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
        	System.out.println(value);
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

	class TableModelExamProc extends AbstractTableModel {
		private static final long serialVersionUID = 0;
        private String[] nomesColunas = {"Data",
        								"Nome",
                                        "Realizada",
                                        "Laudo"
                                        };
       private Object[][] data = {
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""},
    		   {"","","",""},{"","","",""}
       };
 
        public int getColumnCount() {
            return nomesColunas.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return nomesColunas[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
 
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class<?> getColumnClass(int c) {
        	return String.class;
        }
 
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
                return false;
        }
 
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
        	System.out.println(value);
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
 
}
