package br.edu.ifam.snaa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifam.snaa.domain.notificacao.oficial.Doenca;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class DoencaDAO extends GenericDAO<Doenca, Long> {

	private static final long serialVersionUID = 1L;

	public List<Doenca> pesquisar(Doenca doenca) {

		String hql = "select this from  Doenca this where  1 = 1 ";
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		if (Util.isNotNull(doenca.getNome())) {
			hql += " and this.nome like :nome";
			parametros.put("nome","%"+ doenca.getNome() + "%");
		}

		return getResultList(hql, parametros);

	}

}
