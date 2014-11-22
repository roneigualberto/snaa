package br.edu.ifam.snaa.util;


public enum RespostaEnum {
	
	S("Sim"),
	N("Não");
	
	private String descricao;
	
	private RespostaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
