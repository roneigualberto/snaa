package br.edu.ifam.snaa.persistence;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.AtividadeVitima;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class AtividadeVitimaDAO extends JPACrud<AtividadeVitima, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<AtividadeVitima> buscarPor(String descricao) {

		String sql = "select atividade from AtividadeVitima atividade where atividade.descricao like :descricao";
		return createQuery(sql)
				.setParameter("descricao", "%" + descricao + "%")
				.getResultList();

	}

}
