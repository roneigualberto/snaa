package br.edu.ifam.snaa.domain.notificacao.oficial;

public enum NivelAlteracaoEnum {
	
	
	ACIMA_DE("Acima de "),
	ABAIXO_DE("Abaixo de");
	
	private String descricao;
	
	
	private NivelAlteracaoEnum(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String toString() {
		return getDescricao();
	}
	
	
	

}
