package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.oficial.Sintoma;
import br.edu.ifam.snaa.domain.notificacao.oficial.TipoSintomaEnum;
import br.edu.ifam.snaa.persistence.SintomaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class SintomaBC extends DelegateCrud<Sintoma, Long, SintomaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	
	public List<Sintoma> buscarPor(String descricao,TipoSintomaEnum tipo) {
		
		
		return getDelegate().buscarPor(descricao,tipo);
		
	}
	
}
