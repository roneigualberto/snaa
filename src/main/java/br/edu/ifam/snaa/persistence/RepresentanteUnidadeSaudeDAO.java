package br.edu.ifam.snaa.persistence;

import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class RepresentanteUnidadeSaudeDAO extends
		GenericDAO<RepresentanteUnidadeSaude, Long> {

	private static final long serialVersionUID = 1L;

	public RepresentanteUnidadeSaude buscarAtivo(Usuario usuario) {
		String sql = "select r from RepresentanteUnidadeSaude r where  r.usuario = :usuario and r.status = :status";

		return (RepresentanteUnidadeSaude) createQuery(sql)
				.setParameter("usuario", usuario)
				.setParameter("status", StatusRegistro.ATIVO).getSingleResult();

	}
	
	public RepresentanteUnidadeSaude buscaPorCpf(String cpf) {
		
		try {
			String sql = "select r from RepresentanteUnidadeSaude r where  r.usuario.cpf = :cpf";

			return (RepresentanteUnidadeSaude) createQuery(sql)
					.setParameter("cpf", cpf)
					.getSingleResult();
		} catch (NoResultException e) {
			
			return null;
		}
		

	}

}
