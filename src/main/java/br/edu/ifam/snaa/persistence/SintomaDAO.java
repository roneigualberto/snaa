package br.edu.ifam.snaa.persistence;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.oficial.Sintoma;
import br.edu.ifam.snaa.domain.notificacao.oficial.TipoSintomaEnum;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class SintomaDAO extends JPACrud<Sintoma, Long> {

	private static final long serialVersionUID = 1L;
	
	
	
	@SuppressWarnings("unchecked")
	public List<Sintoma> buscarPor(String descricao,TipoSintomaEnum tipo) {
		
		
		String sql = "select sintoma from Sintoma sintoma where sintoma.descricao like :descricao and sintoma.tipo = :tipo";
		return  createQuery(sql)
				.setParameter("descricao","%"+descricao+"%")
				.setParameter("tipo",tipo)
				.getResultList();
		
	}
	

}
