package br.edu.ifam.snaa.business;

import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class UsuarioBC extends DelegateCrud<Usuario, Long, UsuarioDAO> {

	private static final long serialVersionUID = 1L;
	
	

	public Usuario efetuarLogin(String login, String senha) {
		return getDelegate().efetuarLogin(login, senha);
	}

	public Usuario buscarPorCPF(String cpf) {
		try {
			
			return getDelegate().buscarPorCPF(cpf);
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	
	
	public void init() {
		
		
	}

}
