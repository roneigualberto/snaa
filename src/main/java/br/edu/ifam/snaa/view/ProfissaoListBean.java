package br.edu.ifam.snaa.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.ifam.snaa.business.ProfissaoBC;
import br.edu.ifam.snaa.domain.notificacao.Profissao;

@ViewController
@NextView("./profissao_edit.jsf")
@PreviousView("./profissao_list.jsf")
public class ProfissaoListBean extends AbstractListPageBean<Profissao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfissaoBC profissaoBC;
	
	@Override
	protected List<Profissao> handleResultList() {
		return this.profissaoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				profissaoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
	
	
	
	public List<Profissao> buscar(String nome) {
		
		return profissaoBC.buscarPorNome(nome);
		
	}

}