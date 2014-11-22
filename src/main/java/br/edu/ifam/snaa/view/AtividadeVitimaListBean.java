package br.edu.ifam.snaa.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.AtividadeVitimaBC;
import br.edu.ifam.snaa.domain.notificacao.AtividadeVitima;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
public class AtividadeVitimaListBean extends
		AbstractListPageBean<AtividadeVitima, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeVitimaBC atividadeVitimaBC;

	@Override
	protected List<AtividadeVitima> handleResultList() {
		return this.atividadeVitimaBC.findAll();
	}

	public List<AtividadeVitima> buscar(String descricao) {

		return atividadeVitimaBC.buscarPor(descricao);

	}

}