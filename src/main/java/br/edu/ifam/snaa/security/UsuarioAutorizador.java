package br.edu.ifam.snaa.security;

import javax.inject.Inject;

import br.edu.ifam.snaa.domain.seguranca.Perfil;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.util.Beans;

public class UsuarioAutorizador implements Authorizer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioSessao usuarioSessao;

	public UsuarioAutorizador() {
		usuarioSessao = Beans.getReference(UsuarioSessao.class);
	}

	@Override
	public boolean hasRole(String role) {
		Perfil perfil = Perfil.valueOf(role);
		return usuarioSessao.temPerfil(perfil);
	}

	@Override
	public boolean hasPermission(String resource, String operation) {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
