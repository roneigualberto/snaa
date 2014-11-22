package br.edu.ifam.snaa.domain.notificacao;

public enum TipoNotificacaoEnum {
	
	POR_UNIDADE_SAUDE("Por Unidade de Saúde"),
	NAO_OFICIAL("Não Oficial");
	
	
	private String descricao;
	
	
	private TipoNotificacaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoNotificacaoEnum convert(int ordinal) {
		
		for (TipoNotificacaoEnum tipoNotificacaoEnum : values()) {
			
			if(ordinal == tipoNotificacaoEnum.ordinal()) {
				return tipoNotificacaoEnum;
			}
			
		}
		return null;
	}

}
