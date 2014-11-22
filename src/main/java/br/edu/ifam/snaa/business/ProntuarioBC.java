package br.edu.ifam.snaa.business;

import br.edu.ifam.snaa.domain.notificacao.oficial.Prontuario;
import br.edu.ifam.snaa.persistence.ProntuarioDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ProntuarioBC extends DelegateCrud<Prontuario, Long, ProntuarioDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
