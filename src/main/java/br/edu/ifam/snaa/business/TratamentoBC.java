package br.edu.ifam.snaa.business;

import br.edu.ifam.snaa.domain.notificacao.oficial.Tratamento;
import br.edu.ifam.snaa.persistence.TratamentoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class TratamentoBC extends DelegateCrud<Tratamento, Long, TratamentoDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
