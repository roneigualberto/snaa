package br.edu.ifam.snaa.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class EstadoDAO extends GenericDAO<Estado, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Estado> pesquisar(Estado estado) {
		StringBuilder hql = new StringBuilder(
				"select this from Estado this where 1 = 1 ");
		Map<String, Object> parametros = buildParametros(hql, estado);
		Query query = createQuery(hql, parametros);

		return query.getResultList();
	}

	private Map<String, Object> buildParametros(StringBuilder hql, Estado estado) {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		if (Util.isNotNull(estado.getNome())) {
			hql.append(" and upper(this.nome) like upper(:nome) ");
			parametros.put("nome", "%" + estado.getNome() + "%");
		}

		if (Util.isNotNull(estado.getSigla())) {
			hql.append(" and upper(this.sigla) like upper(:sigla) ");
			parametros.put("sigla", "%" + estado.getSigla() + "%");
		}

		return parametros;

	}

	@SuppressWarnings("unchecked")
	public Estado buscarPorNome(String nome) {

		try {
			String hql = "select this from Estado this where this.nome = :nome ";

			return (Estado) createQuery(hql).setParameter("nome", nome)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Estado> buscarPorPais(Pais pais) {
		String hql = "select this from Estado this where this.pais = :pais ";

		return createQuery(hql).setParameter("pais", pais).getResultList();
	}

}
