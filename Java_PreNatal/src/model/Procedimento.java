package model;

import java.io.Serializable;


public class Procedimento implements Serializable {

	String nome;
	String date;	
	boolean realizado;
	String laudo;
	
	
	public String getNome() {
		return nome;
	}
	
	public Procedimento(String nome, String date, boolean realizado) {
		this.nome = nome;
		this.date = date;
		this.realizado = realizado;
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
