package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.oficial.QuadroSaida;
import br.edu.ifam.snaa.persistence.QuadroSaidaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class QuadroSaidaBC extends DelegateCrud<QuadroSaida, Long, QuadroSaidaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<QuadroSaida> pesquisar(QuadroSaida quadroSaida) {
		
		return getDelegate().pesquisar(quadroSaida);
	}
	
}
