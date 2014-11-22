package br.edu.ifam.snaa.util;

public enum RelatorioEnum {

	ACIDENTE_POR_MUNICIPIO("Relatório de Acidentes por Município",
			"rel_acidentes_por_municipio.jasper"),
	RANKING_POR_MUNICIPIO("Ranking de acidentes por Município","rel_ranking_municipio.jasper"),
	RANKING_POR_SEXO("Ranking de acidentes por Sexo","rel_ranking_por_sexo.jasper"),
	RANKING_POR_FAIXA_ETARIA("Ranking de acidentes por faixa etária","rel_ranking_faixa_etaria.jasper"),
	RANKING_POR_SINTOMAS("Ranking de acidentes por sintomas","rel_ranking_por_sintomas.jasper"),
	RANKING_POR_QUADRO_SAIDA("Ranking de acidentes por quadro de saída","rel_ranking_por_quadro_saida.jasper"),
	RANKING_POR_LOCAL_ANATOMICO("Ranking de acidentes por local anatômico","rel_ranking_por_local_anatomico.jasper");

	private String path;
	private String descricao;

	private RelatorioEnum(String descricao, String path) {
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
