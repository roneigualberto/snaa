package br.edu.ifam.snaa.business;

import br.edu.ifam.snaa.domain.administrativo.solicitacao.DocumentoAnexo;
import br.edu.ifam.snaa.persistence.DocumentoAnexoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class DocumentoAnexoBC extends DelegateCrud<DocumentoAnexo, Long, DocumentoAnexoDAO> {
	
	private static final long serialVersionUID = 1L;
	
}
