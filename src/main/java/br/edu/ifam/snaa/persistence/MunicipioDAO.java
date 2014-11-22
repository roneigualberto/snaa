package br.edu.ifam.snaa.persistence;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.endereco.Pais;

@PersistenceController
public class MunicipioDAO extends JPACrud<Municipio, Long> {

	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	public List<Municipio> buscarPorNomeEstado(String nome) {
		String sql = "select m from Municipio m where upper(m.estado.nome) = upper(:estado)";
		return createQuery(sql).setParameter("estado", nome).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Municipio> buscarPorEstado(Estado estado) {
		String sql = "select m from Municipio m where m.estado = :estado";
		return createQuery(sql).setParameter("estado", estado).getResultList();
	}

	@Override
	public List<Municipio> findAll() {
		return findByJPQL("select m from Municipio m order by m.nome asc");
	}

}
