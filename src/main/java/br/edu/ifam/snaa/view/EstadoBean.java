package br.edu.ifam.snaa.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.EstadoBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
@PreviousView("/pages/administrativo/cadastros/endereco/estado/estadoList.jsf?faces-redirect=true")
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class EstadoBean extends AbstractEditPageBean<Estado, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoBC estadoBC;

	@Inject
	private JSFUtil jsfUtil;

	@Override
	@Transactional
	public String delete() {
		this.estadoBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.estadoBC.insert(getBean());

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(Estado.class));
		return null;
	}

	@Override
	@Transactional
	public String update() {
		this.estadoBC.update(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		return null;
	}

	@Override
	protected Estado handleLoad(Long id) {
		return this.estadoBC.load(id);
	}
	
	

	

}