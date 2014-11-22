package br.edu.ifam.snaa.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.edu.ifam.snaa.domain.endereco.TipoLocalizacao;
import br.edu.ifam.snaa.persistence.TipoLocalizacaoDAO;

@BusinessController
public class TipoLocalizacaoBC extends DelegateCrud<TipoLocalizacao, Long, TipoLocalizacaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
