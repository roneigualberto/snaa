package br.edu.ifam.snaa.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.EscolaridadeBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.Escolaridade;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
@PreviousView("/pages/administrativo/cadastros/escolaridade/escolaridadeList.jsf")
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class EscolaridadeBean extends
		AbstractEditPageBean<Escolaridade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EscolaridadeBC escolaridadeBC;
	
	@Inject
	private JSFUtil  jsfUtil;

	@Override
	@Transactional
	public String delete() {
		this.escolaridadeBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.escolaridadeBC.insert(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(Escolaridade.class));
		return null;
	}

	@Override
	@Transactional
	public String update() {
		this.escolaridadeBC.update(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(Escolaridade.class));
		return null;
	}

	@Override
	protected Escolaridade handleLoad(Long id) {
		return this.escolaridadeBC.load(id);
	}

}