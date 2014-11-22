package br.edu.ifam.snaa.domain.seguranca;

/**
 * 
 * 
 * @author Ronei
 * 
 * Enumeração que possui os perfils que o sistema possui.
 */
public enum Perfil {

	ADMINISTRADOR("Administrador"), 
	PROFISSIONAL_SAUDE("Profissional de Saúde"), 
	REPRESENTANTE_UNIDADE_SAUDE(
			"Unidade de Saúde");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
