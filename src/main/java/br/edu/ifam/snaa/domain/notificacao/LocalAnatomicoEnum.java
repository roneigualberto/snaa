package br.edu.ifam.snaa.domain.notificacao;

public enum LocalAnatomicoEnum {
	
	CABECA("Cabeça"),
	PESCOCO("Pescoço"),
	OMBRO_ESQUERDO("Ombro Esquerdo"),
	OMBRO_DIREITO("Ombro Direito"),
	BRACO_ESQUERDO("Braço Esquerdo"),
	BRACO_DIREITO("Braço Direito"),
	ANTEBRACO_ESQUERDO("Antebraço Esquerdo"),
	ANTEBRACO_DIREITO("Antebraço Direito"),
	MAO_ESQUERDA("Mão Esquerda"),
	MAO_DIREITA("Mão Direita"),
	COXA_ESQUERDA("Coxa Esquerda"),
	COXA_DIREITA("Coxa Direita"),
	PE_ESQUERDO("Pé Esquerdo"),
	PE_DIREITO("Pé Direito"),
	PERNA_DIREITA("Perna Direita"),
	PERNA_ESQUERDA("Perna Esquerda");
	
	private String nome;
	
	
	private LocalAnatomicoEnum(String nome)  {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
 
	

}
