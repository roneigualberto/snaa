package br.edu.ifam.snaa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifam.snaa.domain.endereco.Logradouro;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class LogradouroDAO extends GenericDAO<Logradouro, Long> {

	private static final long serialVersionUID = 1L;

	public List<Logradouro> buscar(Logradouro logradouro) {
		StringBuilder hql = new StringBuilder(
				"select l from Logradouro l where l.bairro = :bairro ");
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append("and upper(l.descricao) like upper(:descricao) ");
		
		
		parametros.put("descricao","%"+ logradouro.getDescricao() +"%");
		parametros.put("bairro", logradouro.getBairro());

		if (logradouro.getTipoLogradouro() != null) {
			hql.append("and l.tipoLogradouro = :tipo");
			parametros.put("tipo", logradouro.getTipoLogradouro());
		}

		return getResultList(hql, parametros);
	}

}
