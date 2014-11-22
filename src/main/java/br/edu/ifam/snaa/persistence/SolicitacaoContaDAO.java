package br.edu.ifam.snaa.persistence;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.SolicitacaoConta;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class SolicitacaoContaDAO extends JPACrud<SolicitacaoConta, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<SolicitacaoConta> listarPorStatus(StatusRegistro status) {
		
	
		

		String sql = "select s from SolicitacaoConta s where s.status =:status order by dataEnvio desc";
		
		Query query = createQuery(sql);
		
		Pagination pagination = getPagination();

		if (pagination != null) {

			pagination.setTotalResults(rowCountPendentes(status));
					
			query.setFirstResult(pagination.getFirstResult());

			query.setMaxResults(pagination.getPageSize());

		}
		
		query.setParameter("status", status);

		return query.getResultList();

	}

	private int rowCountPendentes(StatusRegistro status) {

		StringBuilder hql = new StringBuilder(
				"select count(s) from SolicitacaoConta s where s.status =:status  order by dataEnvio desc");

		Query query = createQuery(hql.toString());

		query.setParameter("status", status).setParameter("status", status);

		return ((Long) query.getSingleResult()).intValue();
	}

}
