package br.edu.ifam.snaa.domain.notificacao.oficial;

public enum SituacaoAnimal {
	
	FERIDO("Ferido"),
	MORTO("Morto");
	
	private String descricao;
	
	
	private SituacaoAnimal(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	

}
