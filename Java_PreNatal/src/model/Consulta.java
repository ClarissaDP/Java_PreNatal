package model;
import java.io.Serializable;


public class Consulta implements Serializable {
	
	String data_cons;	// data Consulta
	boolean realizada;
	Usuario medico;
	String presc;	// prescrição
	String anam;	// anamnese
	String diag;	// diagnóstico	
	String prog;	// prognostico
	
	
	public Consulta(String data_cons, boolean realizada) {
		this.data_cons = data_cons;
		this.realizada = realizada;
	}

	public String getData_cons() {
		return data_cons;
	}
	
	public void setData_cons(String data_cons) {
		this.data_cons = data_cons;
	}
	
	public boolean isRealizada() {
		return realizada;
	}
	
	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}
	
	public Usuario getMedico() {
		return medico;
	}
	
	public void setMedico(Usuario medico) {
		this.medico = medico;
	}
	
	public String getPresc() {
		return presc;
	}
	
	public void setPresc(String presc) {
		this.presc = presc;
	}
	
	public String getAnam() {
		return anam;
	}
	
	public void setAnam(String anam) {
		this.anam = anam;
	}
	
	public String getDiag() {
		return diag;
	}
	
	public void setDiag(String diag) {
		this.diag = diag;
	}
	
	public String getProg() {
		return prog;
	}
	
	public void setProg(String prog) {
		this.prog = prog;
	}
		
	
}
