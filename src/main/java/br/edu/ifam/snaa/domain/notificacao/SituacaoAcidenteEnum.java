package br.edu.ifam.snaa.domain.notificacao;

public enum SituacaoAcidenteEnum {
	
	PENDENTE("Pendente"),
	CONCLUIDO("Concluído");
	
	private String situacao;
	
	private SituacaoAcidenteEnum(String situacao) {
		this.situacao = situacao;
	}

	public String getSituacao() {
		return situacao;
	}
	
	
	
	
	

}
