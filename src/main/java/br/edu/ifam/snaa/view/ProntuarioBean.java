package br.edu.ifam.snaa.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.AcidenteBC;
import br.edu.ifam.snaa.business.DoencaBC;
import br.edu.ifam.snaa.business.ProntuarioBC;
import br.edu.ifam.snaa.business.SintomaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.edu.ifam.snaa.domain.notificacao.oficial.Atendimento;
import br.edu.ifam.snaa.domain.notificacao.oficial.Doenca;
import br.edu.ifam.snaa.domain.notificacao.oficial.Exame;
import br.edu.ifam.snaa.domain.notificacao.oficial.Prontuario;
import br.edu.ifam.snaa.domain.notificacao.oficial.Sintoma;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@ViewController
@RequiredRole(Constantes.PERFIL_PROFISSIONAL_SAUDE)
public class ProntuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Prontuario prontuario;

	@Inject
	private Acidente acidente;

	@Inject
	private Sintoma sintomaSistemico;

	private Sintoma sintomaLocal;

	@Inject
	private Exame exame;

	@Inject
	private JSFUtil jsfUtil;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private ProntuarioBC prontuarioBC;

	@Inject
	private SintomaBC sintomaBC;

	@Inject
	private DoencaBC doencaBC;

	@Inject
	private AcidenteBC acidenteBC;

	@Inject
	private AcidenteBean acidenteBean;

	@PostConstruct
	public void init() {
		Atendimento atendimento = acidenteBean.getBean().getUltimoAtendimento();

		this.acidente = acidenteBean.getBean();

		if (atendimento.getProntuario() != null) {
			this.prontuario = atendimento.getProntuario();
		} else {
			this.prontuario = new Prontuario(atendimento);
		}

	}

	public Sintoma getSintomaSistemico() {
		return sintomaSistemico;
	}

	public void setSintomaSistemico(Sintoma sintomaSistemico) {
		this.sintomaSistemico = sintomaSistemico;
	}

	public void addSintomaSistemico() {
		String value = bundle.getString("campo.obrigatorio");
		if (sintomaSistemico == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:formulario:sintomaSistemico", msg);

			return;
		}

		if (sintomaSistemico.getId() != null) {
			sintomaSistemico = sintomaBC.load(sintomaSistemico.getId());
		}

		this.prontuario.adicionarSintomaSistemico(sintomaSistemico);
		sintomaSistemico = null;
	}

	public void addSintomaLocal() {
		String value = bundle.getString("campo.obrigatorio");
		if (sintomaLocal == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:formulario:sintomaLocal", msg);

			return;
		}

		this.prontuario.adicionarSintomaLocal(sintomaLocal);
		sintomaLocal = null;
	}

	public void removeSintomaSistemico() {
		this.prontuario.removerSintomaSistemico(sintomaSistemico);
		sintomaSistemico = null;
	}

	public void removeSintomaLocal() {
		this.prontuario.removerSintomaSistemico(sintomaLocal);
		sintomaLocal = null;
	}

	public List<String> getDoencasSelect() {
		List<Doenca> doencas = this.prontuario.getDoencas();
		List<String> doencasList = new ArrayList<String>();

		for (Doenca doenca : doencas) {
			doencasList.add(doenca.getId().toString());
		}

		return doencasList;

	}

	public void setDoencasSelect(List<String> doencas) {
		Long idDoenca = null;
		List<Doenca> doencaList = new ArrayList<Doenca>();

		for (String strIdDoenca : doencas) {

			idDoenca = Long.valueOf(strIdDoenca);
			doencaList.add(doencaBC.load(idDoenca));
		}
		this.prontuario.setDoencas(doencaList);
	}

	public void escolherLocal() {
	}

	public void desfazerLocal() {
		Long idAcidente = this.prontuario.getAtendimento().getAcidente()
				.getId();
		Acidente acidente = this.prontuario.getAtendimento().getAcidente();
		Acidente acidente2 = acidenteBC.load(idAcidente);

		acidente.setLocalAnatomico(acidente2.getLocalAnatomico());

	}

	public Sintoma getSintomaLocal() {
		return sintomaLocal;
	}

	public void setSintomaLocal(Sintoma sintomaLocal) {
		this.sintomaLocal = sintomaLocal;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Sintoma> getSintomasSistemico() {

		return this.prontuario.getSintomasSistemico();

	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}

	public Acidente getAcidente() {
		return acidente;
	}

	private void loadSintomas() {
		List<Sintoma> sintomas = this.prontuario.getSintomas();
		List<Sintoma> s = new ArrayList<Sintoma>();
		for (Sintoma sintoma : sintomas) {
			if (sintoma.getId() != null) {
				s.add(sintomaBC.load(sintoma.getId()));
			} else {
				s.add(sintoma);
			}

		}

		this.prontuario.setSintomas(s);
	}

	@Transactional
	public String update() {
		Prontuario prontuario = this.prontuario;
		String key = null;

		if (!validarProntuario()) {
			return null;
		}

		loadSintomas();

		if (prontuario.getId() == null) {
			prontuarioBC.insert(prontuario);
			key = "prontuario.save.sucesso";
		} else {
			prontuarioBC.update(prontuario);
			key = "prontuario.update.sucesso";
		}

		Acidente ac = this.acidenteBC.load(acidente.getId());

		ac.setLocalAnatomico(acidente.getLocalAnatomico());
		// ac.setAtividadeVitima(acidente.getAtividadeVitima());

		acidenteBC.update(ac);

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO, key);

		return null;
	}

	private boolean validarProntuario() {
		boolean retorno = true;
		String value = bundle.getString("campo.obrigatorio");

		if (this.prontuario.getSintomas().isEmpty()) {
			jsfUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"sintoma.obrigatorio");
			retorno = false;
		}

		if (acidente.getLocalAnatomico() == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:formulario:localAnatomico", msg);
			retorno = false;

		}

		/*if (acidente.getAtividadeVitima() == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:formulario:atividade", msg);
			retorno = false;

		}*/

		return retorno;

	}

}
