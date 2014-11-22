package br.edu.ifam.snaa.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.TratamentoBC;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.edu.ifam.snaa.domain.notificacao.oficial.Atendimento;
import br.edu.ifam.snaa.domain.notificacao.oficial.EstadoExameEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.Exame;
import br.edu.ifam.snaa.domain.notificacao.oficial.InternadoEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.TipoExameEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.Tratamento;
import br.edu.ifam.snaa.util.JSFUtil;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@ViewController

public class TratamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Acidente acidente;

	@Inject
	private Tratamento tratamento;

	@Inject
	private Exame exame;

	@Inject
	private JSFUtil jsfUtil;
	
	@Inject
	private ResourceBundle bundle;

	@Inject
	private TratamentoBC tratamentoBC;

	
	@Inject
	private AcidenteBean acidenteBean;

	@Inject
	private TipoExameBean tipoExameBean;

	private List<TipoExameEnum> listTipoExame;

	@PostConstruct
	public void init() {
		Atendimento atendimento = acidenteBean.getBean().getUltimoAtendimento();

		this.acidente = acidenteBean.getBean();

		if (atendimento.getTratamento() != null) {
			this.tratamento = atendimento.getTratamento();
		} else {
			this.tratamento = new Tratamento(atendimento);
		}

	}

	public Acidente getAcidente() {
		return acidente;
	}
	
	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public void adicionarExame() {

		if (!validarExame()) {
			return;
		}

		this.tratamento.adicionarExame(exame);

		this.exame = new Exame();
		loadListTipoExame();
	}

	public void removeExame() {
		this.tratamento.removerExame(exame);
		loadListTipoExame();
		exame = new Exame();
	}

	@Transactional
	public String update() {
		Tratamento tratamento = this.tratamento;
		String key = null;

		if (!validarTratamento()) {
			return null;
		}

		if (tratamento.getId() == null) {
			tratamentoBC.insert(tratamento);
			key = "tratamento.save.sucesso";
		} else {
			tratamentoBC.update(tratamento);
			key = "tratamento.update.sucesso";
		}

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO, key);

		return null;
	}

	private boolean validarExame() {
		boolean result = true;
		String value = bundle.getString("campo.obrigatorio");
		if (exame.getTipoExame() == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:tratamentoForm:tipoExame", msg);
			result = false;

		}

		if (exame.getEstadoExame() == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:tratamentoForm:estadoExame", msg);
			result = false;

		}
		
		boolean alterado = exame.isAlterado();

		if (exame.getNivelAlteracao() == null && alterado) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:tratamentoForm:nivelAlteracao", msg);
			result = false;

		}
		if (exame.getValor() == 0 && alterado) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:tratamentoForm:valorAlteracao", msg);
			result = false;

		}

		return result;

	}

	private boolean validarTratamento() {
		boolean retorno = true;

		if (tratamento.getCategoriaSistemico() == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"obrigatorio", null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:tratamentoForm:tipoMedicamentoSistemico", msg);
			retorno = false;

		}
		
		if (tratamento.getInternado() == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"obrigatorio", null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:tratamentoForm:internado", msg);
			retorno = false;

		} else {
			if (InternadoEnum.S.equals(tratamento.getInternado())
					&& Util.isNull(tratamento.getDias())) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "obrigatorio", null);
				FacesContext.getCurrentInstance().addMessage(
						"tabView:tratamentoForm:tempo", msg);
				retorno = false;

			}
		}

		return retorno;

	}

	public List<TipoExameEnum> getListTipoExame() {

		if (listTipoExame == null) {
			loadListTipoExame();
		}

		return listTipoExame;

	}

	private void loadListTipoExame() {
		listTipoExame = new ArrayList<TipoExameEnum>(
				Arrays.asList(tipoExameBean.getList()));

		for (Exame exame : this.tratamento.getExames()) {

			if (listTipoExame.contains(exame.getTipoExame())) {
				listTipoExame.remove(exame.getTipoExame());
			}

		}
	}
	
	public boolean isHabilitaValorAlteracao() {
		return this.exame.getEstadoExame() == null || EstadoExameEnum.ALTERADO.equals(this.exame.getEstadoExame());
	}
	
	
	

	

}
