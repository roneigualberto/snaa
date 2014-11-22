package br.edu.ifam.snaa.domain.notificacao;

public enum PeriodoEnum {

	MADRUGADA("Madrugada", "Entre 00:00 e 05:59"),
	MANHA("Manhã","Entre 06:00 e 11:59"), 
	TARDE("Tarde", "Entre 12:00 e 17:59"),
	NOITE("Noite", "Entre 18:00 e 23:59");

	private String descricao;

	private String faixa;

	private PeriodoEnum(String descricao, String faixa) {
		this.descricao = descricao;
		this.faixa = faixa;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getFaixa() {
		return faixa;
	}
	
	@Override
	public String toString() {
	
		return getDescricao() + " ( " +getFaixa() +" )";
	}

}
