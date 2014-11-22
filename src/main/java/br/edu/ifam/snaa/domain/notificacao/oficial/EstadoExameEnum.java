package br.edu.ifam.snaa.domain.notificacao.oficial;

public enum EstadoExameEnum {
	
	NORMAL("Normal"),
	ALTERADO("Alterado");
	
	private String descricao;
	
	
	private EstadoExameEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	

}
