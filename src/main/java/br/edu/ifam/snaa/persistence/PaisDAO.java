package br.edu.ifam.snaa.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class PaisDAO extends GenericDAO<Pais, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Pais> buscarPorNome(String descricao) {

		String sql = "select pais from Pais pais where pais.nome like :descricao";
		return createQuery(sql)
				.setParameter("descricao", "%" + descricao + "%")
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Pais> pesquisar(Pais pais) {
		StringBuilder hql = new StringBuilder(
				"select pais from Pais pais where 1 = 1 ");
		Map<String, Object> parametros = buildParametros(hql, pais);
		Query query = createQuery(hql, parametros);

		return query.getResultList();
	}

	private Map<String, Object> buildParametros(StringBuilder hql, Pais pais) {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		

		if (Util.isNotNull(pais.getNome())) {
			hql.append(" and upper(pais.nome) like upper(:nome) ");
			parametros.put("nome", "%" + pais.getNome() + "%");
		}
		
		if (Util.isNotNull(pais.getSigla())) {
			hql.append(" and upper(pais.sigla) like upper(:sigla) ");
			parametros.put("sigla", "%" + pais.getSigla() + "%");
		}


		return parametros;

	}

	public Pais buscarPorNomeIdentico(String nome) {

		String sql = "select pais from Pais pais where upper(pais.nome) = :nome";
		return (Pais) createQuery(sql).setParameter("nome", nome)
				.getSingleResult();

	}

	public Pais buscarPorSiglaIdentica(String sigla) {

		String sql = "select pais from Pais pais where pais.sigla = :descricao";
		return (Pais) createQuery(sql).setParameter("descricao", sigla)
				.getSingleResult();

	}
	
	

}
