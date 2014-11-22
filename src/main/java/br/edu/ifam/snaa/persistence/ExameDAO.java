package br.edu.ifam.snaa.persistence;

import br.edu.ifam.snaa.domain.notificacao.oficial.Exame;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class ExameDAO extends JPACrud<Exame, Long> {

	private static final long serialVersionUID = 1L;
	

}
