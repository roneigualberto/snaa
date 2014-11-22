package br.edu.ifam.snaa.view;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.ProfissaoBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.Profissao;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class ProfissaoBean extends AbstractEditPageBean<Profissao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfissaoBC profissaoBC;

	@Override
	@Transactional
	public String delete() {
		this.profissaoBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.profissaoBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.profissaoBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Profissao handleLoad(Long id) {
		return this.profissaoBC.load(id);
	}

}