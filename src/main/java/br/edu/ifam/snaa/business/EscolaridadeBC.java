package br.edu.ifam.snaa.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.edu.ifam.snaa.domain.notificacao.Escolaridade;
import br.edu.ifam.snaa.persistence.EscolaridadeDAO;

@BusinessController
public class EscolaridadeBC extends DelegateCrud<Escolaridade, Long, EscolaridadeDAO> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<Escolaridade> pesquisar(Escolaridade escolaridade) {
		return getDelegate().pesquisar(escolaridade);
	}
	
}
