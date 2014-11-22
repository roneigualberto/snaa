package br.edu.ifam.snaa.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class VitimaDAO extends GenericDAO<Vitima, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Vitima> pesquisar(Vitima vitima) {
		StringBuilder hql = new StringBuilder(
				"select vitima from Vitima vitima join fetch vitima.acidentes acidente where 1 = 1 ");
		Map<String, Object> parametros = buildParametros(hql, vitima);
		
		hql.append("order by acidente.id desc ");
		Query query = createQuery(hql, parametros);

		Pagination pagination = getPagination();

		if (pagination != null) {

			pagination.setTotalResults(this.rowCountPesquisar(vitima));

			query.setFirstResult(pagination.getFirstResult());

			query.setMaxResults(pagination.getPageSize());

		}

		return query.getResultList();
	}

	private Map<String, Object> buildParametros(StringBuilder hql, Vitima vitima) {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		if (Util.isNotNull(vitima.getCpf())) {
			hql.append("and vitima.cpf = :cpf ");
			parametros.put("cpf", vitima.getCpf());
		}

		if (Util.isNotNull(vitima.getRg())) {
			hql.append("and upper(vitima.rg) like upper(:rg) ");
			parametros.put("rg", "%" + vitima.getRg() + "%");
		}

		if (Util.isNotNull(vitima.getNome())) {
			hql.append("and upper(vitima.nome) like upper(:nome) ");
			parametros.put("nome", "%" + vitima.getNome() + "%");
		}

		if (vitima.getDataNascimento() != null) {
			hql.append("and vitima.dataNascimento = :dataNascimento ");
			parametros.put("dataNascimento", vitima.getDataNascimento());
		}

		if (vitima.getSexo() != null) {
			hql.append("and vitima.sexo = :sexo ");
			parametros.put("sexo", vitima.getSexo());
		}

		if (vitima.getProfissionalSaude() != null) {
			hql.append("and vitima.profissionalSaude = :prof ");
			parametros.put("prof", vitima.getProfissionalSaude());
		}

		return parametros;

	}

	private int rowCountPesquisar(Vitima vitima) {

		StringBuilder hql = new StringBuilder(
				"select count(vitima) from Vitima vitima where 1 = 1");
		Map<String, Object> parametros = buildParametros(hql, vitima);
		Query query = createQuery(hql, parametros);

		return ((Long) query.getSingleResult()).intValue();
	}

	public Vitima buscarPorCPF(String cpf) {
		String hql = "select v from Vitima v where v.cpf = :cpf";

		try {
			return (Vitima) createQuery(hql).setParameter("cpf", cpf)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Vitima buscarPorPassaporte(String passaporte) {
		String hql = "select v from Vitima v where v.passaporte = :passaporte";

		try {
			return (Vitima) createQuery(hql).setParameter("passaporte",
					passaporte).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

}
