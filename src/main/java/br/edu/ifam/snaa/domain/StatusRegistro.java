package br.edu.ifam.snaa.domain;

public enum StatusRegistro {
	
	PENDENTE("Não"),
	INATIVO("INATIVO"),
	ATIVO("ATIVO");
	
	
	private String descricao;
	
	private StatusRegistro(String registro) {
		this.descricao  = registro;
	}
	
	public String getDescricao() {
		return descricao;
	}
 
}
