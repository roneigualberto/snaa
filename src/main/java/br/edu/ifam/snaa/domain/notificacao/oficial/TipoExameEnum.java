package br.edu.ifam.snaa.domain.notificacao.oficial;


public enum TipoExameEnum {
	
	
	HEMOGRAMA_COMPLETO("Hemograma Completo"),
	PROTEINA_C_REATIVA("Proteína C Reativa"),
	CREATININA_QUINASE("Creatinina quinase");
	
	
	private String descricao;
	
	
	private TipoExameEnum(String descricao){
		this.descricao = descricao;
		
	}


	public String getDescricao() {
		return descricao;
	}
	
	
	
	
	
	

}
