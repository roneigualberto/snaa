package br.edu.ifam.snaa.domain.notificacao.oficial;

public enum CategoriaMedicamento {
	
	NENHUM("Nenhum"),
	ANTI_INFLAMATORIO("Anti-inflamátório"),
	ANTI_BIOTICO("Antibiótico"),
	ANALGESICO("Analgésico");
	
	private String descricao;
	
	private CategoriaMedicamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
