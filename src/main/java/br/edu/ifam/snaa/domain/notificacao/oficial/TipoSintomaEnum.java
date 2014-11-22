package br.edu.ifam.snaa.domain.notificacao.oficial;

public enum TipoSintomaEnum {
	
	LOCAL("Local"),SISTEMICO("Sistêmico");
	
	private String descricao;
	
	private TipoSintomaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getDescricaoCompleta() {
		return "Sintoma "+getDescricao();
	}
	
	
	@Override
	public String toString() {
		return getDescricaoCompleta();
	}
	

}
