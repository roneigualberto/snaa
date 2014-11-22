package br.edu.ifam.snaa.domain.notificacao.oficial;

public enum InternadoEnum {
	
	S("Sim"),
	N("Não");
	
	
	private String descricao;
	
	private InternadoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
