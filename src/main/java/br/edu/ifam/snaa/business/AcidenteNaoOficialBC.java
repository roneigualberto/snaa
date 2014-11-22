package br.edu.ifam.snaa.business;

import br.edu.ifam.snaa.domain.notificacao.naooficial.AcidenteNaoOficial;
import br.edu.ifam.snaa.persistence.AcidenteNaoOficialDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class AcidenteNaoOficialBC extends DelegateCrud<AcidenteNaoOficial, Long, AcidenteNaoOficialDAO> {

	private static final long serialVersionUID = 1L;

	

	@Override
	public AcidenteNaoOficial insert(AcidenteNaoOficial acidente) {
		return super.insert(acidente);
	}


	

}
