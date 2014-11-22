package br.edu.ifam.snaa.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.QuadroSaidaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.QuadroSaida;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
@PreviousView("/pages/administrativo/cadastros/quadrosaida/quadrosaidaList.jsf")
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class QuadroSaidaBean extends
		AbstractEditPageBean<QuadroSaida, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuadroSaidaBC quadrosaidaBC;
	
	@Inject
	private JSFUtil  jsfUtil;

	@Override
	@Transactional
	public String delete() {
		this.quadrosaidaBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.quadrosaidaBC.insert(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(QuadroSaida.class));
		return null;
	}

	@Override
	@Transactional
	public String update() {
		this.quadrosaidaBC.update(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		return null;
	}

	@Override
	protected QuadroSaida handleLoad(Long id) {
		return this.quadrosaidaBC.load(id);
	}

}