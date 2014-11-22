package br.edu.ifam.snaa.view;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.VitimaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@RequiredRole(Constantes.PERFIL_PROFISSIONAL_SAUDE)
public class VitimaBean extends AbstractEditPageBean<Vitima, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VitimaBC vitimaBC;

	

	@Override
	public String delete() {
		return null;
	}

	@Override
	@Transactional
	public String insert() {

		return null;
	}

	@Override
	@Transactional
	public String update() {
		return null;
	}

	@Override
	protected Vitima handleLoad(Long id) {
		return this.vitimaBC.load(id);
	}




}