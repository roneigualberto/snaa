package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.Profissao;
import br.edu.ifam.snaa.persistence.ProfissaoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ProfissaoBC extends DelegateCrud<Profissao, Long, ProfissaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<Profissao> buscarPorNome(String descricao) {
		
		
		return getDelegate().buscarPorNome(descricao);
		
	}
	
}
