package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.AtividadeVitima;
import br.edu.ifam.snaa.persistence.AtividadeVitimaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AtividadeVitimaBC extends DelegateCrud<AtividadeVitima, Long,AtividadeVitimaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<AtividadeVitima> buscarPor(String descricao) {
		
		
		return getDelegate().buscarPor(descricao);
		
	}
	
}
