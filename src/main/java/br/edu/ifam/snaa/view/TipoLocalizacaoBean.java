package br.edu.ifam.snaa.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.ifam.snaa.business.TipoLocalizacaoBC;
import br.edu.ifam.snaa.domain.endereco.TipoLocalizacao;

@ViewController
@PreviousView("./tipoLocalizacao_list.jsf")
public class TipoLocalizacaoBean extends
		AbstractEditPageBean<TipoLocalizacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoLocalizacaoBC tipoLocalizacaoBC;

	@Override
	@Transactional
	public String delete() {
		this.tipoLocalizacaoBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.tipoLocalizacaoBC.insert(getBean());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String update() {
		this.tipoLocalizacaoBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected TipoLocalizacao handleLoad(Long id) {
		return this.tipoLocalizacaoBC.load(getId());
	}

}