package br.edu.ifam.snaa.persistence;

import java.util.List;

import br.edu.ifam.snaa.domain.endereco.Bairro;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class BairroDAO extends JPACrud<Bairro, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Bairro> buscarPorMunicipio(Municipio municipio) {
		String sql = "select b from Bairro b where b.municipio = :municipio order by  b.nome";
		return createQuery(sql).setParameter("municipio", municipio)
				.getResultList();
	}

}
