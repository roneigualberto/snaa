package br.edu.ifam.snaa.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.edu.ifam.snaa.domain.notificacao.oficial.Doenca;
import br.edu.ifam.snaa.persistence.DoencaDAO;

@BusinessController
public class DoencaBC extends DelegateCrud<Doenca, Long, DoencaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Doenca> pesquisar(Doenca doenca) {
		return getDelegate().pesquisar(doenca);
	}
	
}
