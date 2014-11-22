package br.edu.ifam.snaa.domain;

public enum SexoEnum {
	
	M("Masculino"),F("Feminino");
	
	private SexoEnum(String sexo) {
		this.sexo = sexo;
	}
	
	private String sexo;
	
	
	public String getSexo() {
		return sexo;
	}
	
	
	@Override
	public String toString() {
		return getSexo();
	}

}
