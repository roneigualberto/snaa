package br.edu.ifam.snaa.persistence;

import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class UnidadeSaudeDAO extends GenericDAO<UnidadeSaude, Long> {

	private static final long serialVersionUID = 1L;


	public UnidadeSaude buscarPorCodigo(String codigo) {
		String hql = "select u from UnidadeSaude u where u.codigo = :codigo";

		try {
			return (UnidadeSaude) createQuery(hql).setParameter("codigo", codigo)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

}
