package br.edu.ifam.snaa.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.ifam.snaa.business.TipoLocalizacaoBC;
import br.edu.ifam.snaa.domain.endereco.TipoLocalizacao;

@ViewController
@NextView("./tipoLocalizacao_edit.jsf")
@PreviousView("./tipoLocalizacao_list.jsf")
public class TipoLocalizacaoListBean extends AbstractListPageBean<TipoLocalizacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoLocalizacaoBC tipoLocalizacaoBC;
	
	@Override
	protected List<TipoLocalizacao> handleResultList() {
		return this.tipoLocalizacaoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				tipoLocalizacaoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}