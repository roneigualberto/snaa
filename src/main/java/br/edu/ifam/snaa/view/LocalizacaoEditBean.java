package br.edu.ifam.snaa.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.ifam.snaa.business.LocalizacaoBC;
import br.edu.ifam.snaa.domain.endereco.Localizacao;

@ViewController
@PreviousView("./localizacao_list.jsf")
public class LocalizacaoEditBean extends AbstractEditPageBean<Localizacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocalizacaoBC localizacaoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.localizacaoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.localizacaoBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.localizacaoBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected Localizacao handleLoad(Long id){
		return this.localizacaoBC.load(id);
	}

}