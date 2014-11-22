package br.edu.ifam.snaa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifam.snaa.domain.notificacao.Arraia;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class ArraiaDAO extends GenericDAO<Arraia, Long> {

	private static final long serialVersionUID = 1L;

	public List<Arraia> pesquisar(Arraia arraia) {

		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();

		hql.append("select arraia from Arraia arraia where 1=1 ");

		if (Util.isNotNull(arraia.getNome())) {
			hql.append(" and upper(arraia.nome) like upper(:nome)");
			parametros.put("nome", "%" + arraia.getNome() + "%");

		}

		if (Util.isNotNull(arraia.getNomeCientifico())) {
			hql.append(" and upper(arraia.nomeCientifico) like upper(:nomeCientifico)");
			parametros.put("nomeCientifico", "%" + arraia.getNomeCientifico()
					+ "%");

		}

		return getResultList(hql, parametros);

	}

	public Arraia buscarPorNomeIdentico(String nome) {

		String sql = "select Arraia from Arraia Arraia where Arraia.nome = :descricao";
		return (Arraia) createQuery(sql).setParameter("descricao", nome)
				.getSingleResult();

	}

	public Arraia buscarPorNomeCientificoIdentico(String nomeCientifico) {

		String sql = "select Arraia from Arraia arraia where arraia.nomeCientifico = :nomeCientifico";
		return (Arraia) createQuery(sql).setParameter("nomeCientifico",
				nomeCientifico).getSingleResult();

	}

}
