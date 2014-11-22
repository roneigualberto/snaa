package br.edu.ifam.snaa.domain.relatorio;

import java.io.Serializable;

import br.edu.ifam.snaa.domain.SexoEnum;
import br.edu.ifam.snaa.domain.notificacao.PeriodoEnum;
import br.edu.ifam.snaa.domain.notificacao.TipoNotificacaoEnum;

public class RelatorioAcidente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TipoNotificacaoEnum tipoNotificacao;
	private String municipio;
	private String periodo="";
	private SexoEnum sexo;
	private String quadroSaida;
	private Long numeroAcidente;
	
	
	

	public RelatorioAcidente(int tipoNotificacao,String municipio, PeriodoEnum periodo, SexoEnum sexo,
			String quadroSaida, Long numeroAcidente) {
		super();
		this.tipoNotificacao = TipoNotificacaoEnum.convert(tipoNotificacao);
		this.municipio = municipio;
		this.periodo = periodo.toString();
		this.sexo = sexo;
		this.quadroSaida = quadroSaida;
		this.numeroAcidente = numeroAcidente;
	}

	public TipoNotificacaoEnum getTipoNotificacao() {
		return tipoNotificacao;
	}

	public void setTipoNotificacao(TipoNotificacaoEnum tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getQuadroSaida() {
		return quadroSaida;
	}

	public void setQuadroSaida(String quadroSaida) {
		this.quadroSaida = quadroSaida;
	}

	public Long getNumeroAcidente() {
		return numeroAcidente;
	}

	public void setNumeroAcidente(Long numeroAcidente) {
		this.numeroAcidente = numeroAcidente;
	}

}
