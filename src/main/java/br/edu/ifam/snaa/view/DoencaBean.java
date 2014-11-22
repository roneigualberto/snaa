package br.edu.ifam.snaa.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.DoencaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.Doenca;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
@PreviousView("/pages/administrativo/cadastros/doenca/doencaList.jsf")
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class DoencaBean extends AbstractEditPageBean<Doenca, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DoencaBC doencaBC;
	
	@Inject
	private JSFUtil jsfUtil;
	
	@Override
	@Transactional
	public String delete() {
		this.doencaBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.doencaBC.insert(getBean());
		
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(Doenca.class));
		return null;
	}
	
	@Override
	@Transactional
	public String update() {
		this.doencaBC.update(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		return null;
	}
	
	@Override
	protected Doenca handleLoad(Long id) {
		return this.doencaBC.load(id);
	}

}