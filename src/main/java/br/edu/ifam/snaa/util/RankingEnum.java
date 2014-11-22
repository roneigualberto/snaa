package br.edu.ifam.snaa.util;

public enum RankingEnum {
	
	RANKING_POR_MUNICIPIO("",""),
	RANKING_POR_GENERO("",""),
	RANKING_POR_FAIXA_ETARIA("","");
	
	private String descricao;
	private String path;

	private RankingEnum(String descricao,String path) {
		this.descricao = descricao;
		this.path = path;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	
	
	public String getPath() {
		return path;
	}

}
