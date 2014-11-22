package br.edu.ifam.snaa.business;

import br.edu.ifam.snaa.domain.notificacao.naooficial.VitimaNaoOficial;
import br.edu.ifam.snaa.persistence.VitimaNaoOficialDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class VitimaNaoOficialBC extends DelegateCrud<VitimaNaoOficial, Long, VitimaNaoOficialDAO> {

	private static final long serialVersionUID = 1L;

	
}
