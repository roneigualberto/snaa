package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.persistence.LocalizacaoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class LocalizacaoBC extends DelegateCrud<Localizacao, Long, LocalizacaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Localizacao> buscar(Localizacao localizacao) {
		return getDelegate().buscar(localizacao);
	}
	
	
	
}
