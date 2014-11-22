package br.edu.ifam.snaa.business;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.Administrador;
import br.edu.ifam.snaa.domain.seguranca.Perfil;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.persistence.AdministradorDAO;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class AdministradorBC extends
		DelegateCrud<Administrador, Long, AdministradorDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;

	public Administrador buscarAtivo(Usuario usuario) {

		try {
			return getDelegate().buscarAtivo(usuario);
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public Administrador update(Administrador administrador) {

		usuarioBC.update(administrador.getUsuario());
		return super.update(administrador);
	}

	@Override
	public Administrador insert(Administrador administrador) {

		Usuario usuario = administrador.getUsuario();

		if (Util.isNotNull(usuario.getId())) {
			usuario = usuarioBC.load(usuario.getId());
		}

		administrador.setUsuario(usuario);

		administrador.getUsuario().addPerfil(Perfil.ADMINISTRADOR);
		administrador.getUsuario().setStatus(StatusRegistro.ATIVO);
		administrador.setStatus(StatusRegistro.ATIVO);

		return super.insert(administrador);
	}

	public List<Administrador> pesquisar(Administrador administrador) {

		return getDelegate().pesquisar(administrador);
	}

	@Transactional
	public void inativar(Administrador administrador) {
		Usuario usuario = usuarioBC.load(administrador.getUsuario().getId());

		administrador.setStatus(StatusRegistro.INATIVO);
		usuario.removePerfil(Perfil.ADMINISTRADOR);
		usuarioBC.update(usuario);
		update(administrador);
	}

	@Transactional
	public void reativar(Administrador administrador) {
		Usuario usuario = usuarioBC.load(administrador.getUsuario().getId());
		usuario.addPerfil(Perfil.ADMINISTRADOR);
		usuarioBC.update(usuario);
		administrador.setStatus(StatusRegistro.ATIVO);
		update(administrador);
	}

}
