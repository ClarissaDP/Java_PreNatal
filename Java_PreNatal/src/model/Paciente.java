package model;


import java.io.Serializable;

public class Paciente implements Serializable {

    private String nome;
    private String dn;		// data de nascimento
    private int tel;
    private String dum;		// dia da última menstruação
    private int cpf;
    private String end;
    private String plano;	// plano de saúde
    private int numCad;		// número de cadastro
    private String dataEnt;	// data de entrada
    private String observ;	// observações
    
   
    public Paciente(String nome, String dn, int tel, String dum, int cpf, String end, 
						String plano, int numCad, String dataEnt, String observ) {
    	this.nome = nome;
    	this.dn = dn;
    	this.tel = tel;
    	this.dum = dum;
    	this.cpf = cpf;
    	this.end = end;
    	this.plano = plano;
    	this.numCad = numCad;
    	this.dataEnt = dataEnt;
    	this.observ = observ;
    }


    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getDN(){
    	return dn;
    }
    public void setDN(String dn){
    	this.dn = dn;
    }

    public int getTel(){
    	return tel;
    }
    public void setTel(int tel){
        this.tel = tel;
    }

    public String getDum(){
    	return dum;
    }
    public void setDum(String dum){
    	this.dum = dum;
    }

    public int getCpf(){
    	return cpf;
    }
    public void setCpf(int cpf){
    	this.cpf = cpf;
    }

    public String getEnd(){
    	return end;
    }
    public void setEnd(String end){
    	this.end = end;
    }

    public String getPlano(){
    	return plano;
    }
    public void setPlano(String plano){
    	this.plano = plano;
    }

    public int getNumCad(){
    	return numCad;
    }
    public void setNumCad(int numCad){
    	this.numCad = numCad;
    }

	public String getDataEnt() {
		return dataEnt;
	}
	public void setDataEnt(String dataEnt) {
		this.dataEnt = dataEnt;
	}

	public String getObserv() {
		return observ;
	}
	public void setObserv(String observ) {
		this.observ = observ;
	}
    
    
    
}
