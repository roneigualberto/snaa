package br.edu.ifam.snaa.persistence;

import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class UsuarioDAO extends JPACrud<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	public Usuario efetuarLogin(String login, String senha) {

		try {

			String sql = "select usuario from Usuario usuario  where usuario.cpf = :plogin and usuario.senha = :psenha ";
			return (Usuario) createQuery(sql).setParameter("plogin", login)
					.setParameter("psenha", senha).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public Usuario buscarPorCPF(String cpf) {
		String hql = "select u from Usuario u where u.cpf = :cpf";

		return (Usuario) createQuery(hql).setParameter("cpf", cpf)
				.getSingleResult();

	}

}
