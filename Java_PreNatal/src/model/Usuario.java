package model;
import java.io.Serializable;

 public class Usuario implements Serializable {

    private String nome;
    private String crm;
    private String login;
    private String senha;

    public Usuario(String nome, String crm, String login, String senha) {
    	this.nome = nome;
    	this.crm = crm;
    	this.login = login;
    	this.senha = senha;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCrm() {
        return crm;
    }
    
    public void setCrm(String crm) {
        this.crm = crm;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
