package br.edu.ifam.snaa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifam.snaa.domain.notificacao.oficial.QuadroSaida;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class QuadroSaidaDAO extends GenericDAO<QuadroSaida, Long> {

	private static final long serialVersionUID = 1L;
	
	
	
	public List<QuadroSaida> pesquisar(QuadroSaida escolaridade) {

		String hql = "select this from  QuadroSaida this where  1 = 1 ";
		Map<String, Object> parametros = new HashMap<String, Object>();

		if (Util.isNotNull(escolaridade.getDescricao())) {
			hql += " and this.descricao like :descricao";
			parametros.put("descricao", "%" + escolaridade.getDescricao() + "%");
		}

		return getResultList(hql, parametros);

	}
	

}
