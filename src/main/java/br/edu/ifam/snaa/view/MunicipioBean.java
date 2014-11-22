package br.edu.ifam.snaa.view;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.MunicipioBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class MunicipioBean extends AbstractEditPageBean<Municipio, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MunicipioBC municipioBC;
	


	@Override
	@Transactional
	public String delete() {
		this.municipioBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.municipioBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.municipioBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected Municipio handleLoad(Long id) {
		return this.municipioBC.load(id);
	}

}