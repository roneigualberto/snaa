package br.edu.ifam.snaa.view.dto;

import java.io.Serializable;
import java.util.Date;

import br.edu.ifam.snaa.domain.SexoEnum;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.notificacao.PeriodoEnum;
import br.edu.ifam.snaa.domain.notificacao.TipoNotificacaoEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.QuadroSaida;

public class AcidenteDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoNotificacaoEnum tipoNotificacao;
	private Municipio municipio;
	private Date dataInicio;
	private Date dataFim;
	private PeriodoEnum periodo;
	private SexoEnum sexo;
	private QuadroSaida quadroSaida;
	public TipoNotificacaoEnum getTipoNotificacao() {
		return tipoNotificacao;
	}
	public void setTipoNotificacao(TipoNotificacaoEnum tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public PeriodoEnum getPeriodo() {
		return periodo;
	}
	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public QuadroSaida getQuadroSaida() {
		return quadroSaida;
	}
	public void setQuadroSaida(QuadroSaida quadroSaida) {
		this.quadroSaida = quadroSaida;
	}
	
	public String getFiltroData() {

		if (getDataInicio() != null && getDataFim() == null) {
			
			return "Depois de: ";
		
		} else if (getDataInicio() == null && getDataFim() != null) {
			return "Antes de: ";
		}
		else if (getDataInicio() != null && getDataFim() != null) {
			return "Entre: ";
		}
		
		return null;
	}
	
	
	

}
