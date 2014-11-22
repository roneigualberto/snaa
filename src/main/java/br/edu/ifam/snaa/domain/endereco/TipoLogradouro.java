package br.edu.ifam.snaa.domain.endereco;

public enum TipoLogradouro {
	
	OUTROS("Outros"),
	ALAMEDA("Alameda"),
	AVENIDA("Avenida"),
	ESTRADA("Estrada"),
	QUADRA("Quadra"),
	VIA("Via"),
	VIADUTO("Viaduto"),
	RODOVIA("RODOVIA"),
	RUA("Rua"),
	TRAVESSA("Travessa");
	
	
	
	
	
	private String descricao;
	
	
	private TipoLogradouro(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao.toUpperCase();
	}
}
	