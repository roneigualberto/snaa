package br.edu.ifam.snaa.persistence;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.Profissao;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class ProfissaoDAO extends JPACrud<Profissao, Long> {

	private static final long serialVersionUID = 1L;
	
	
	
	@SuppressWarnings("unchecked")
	public List<Profissao> buscarPorNome(String descricao) {
		
		
		String sql = "select profissao from Profissao profissao where profissao.descricao like :descricao";
		return  createQuery(sql)
				.setParameter("descricao","%"+descricao+"%")
				.getResultList();
		
	}
	

}
