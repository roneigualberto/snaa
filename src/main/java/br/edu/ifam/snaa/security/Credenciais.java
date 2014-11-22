package br.edu.ifam.snaa.security;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Credenciais implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String nome) {
		this.senha = nome;
	}
	
	

}
