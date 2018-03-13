package model;

import java.io.Serializable;


// Calendar cal = Calendar.getInstance();
// Date: https://examples.javacodegeeks.com/core-java/util/date/java-util-date-example-how-to-use-date/


public class Exame implements Serializable {

	String nome;
	String date;	
	boolean realizado;
	String laudo;
	// String nome_dir;		??
	
	public Exame(String nome, String date, boolean realizado, String laudo) {
		super();
		this.nome = nome;
		this.date = date;
		this.realizado = realizado;
		this.laudo = laudo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean isRealizado() {
		return realizado;
	}
	
	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public String getLaudo() {
		return laudo;
	}

	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}
	

}
