package br.edu.ifam.snaa.security;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.UsuarioBC;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;

public class UsuarioAutenticador implements Authenticator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Inject
	private UsuarioSessao usuarioSessao;

	@Inject
	private Credenciais credenciais;

	@Inject
	private UsuarioBC usuarioBC;

	@Override
	public void authenticate() throws AuthenticationException {

		Usuario usuario = usuarioBC.efetuarLogin(credenciais.getLogin(),
				credenciais.getSenha());

		if (usuario == null) {
			throw new AuthenticationException("login.erro");
		}

		usuarioSessao.carregarPerfis(usuario);

	}

	@Override
	public void unauthenticate() throws Exception {
		usuarioSessao.setUsuario(null);

	}

	@Override
	public User getUser() {
		return usuarioSessao.isLogado() ? usuarioSessao : null;
	}

}
