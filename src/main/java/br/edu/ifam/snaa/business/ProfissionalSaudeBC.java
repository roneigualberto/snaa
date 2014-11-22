package br.edu.ifam.snaa.business;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.domain.seguranca.Perfil;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.persistence.ProfissionalSaudeDAO;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class ProfissionalSaudeBC extends
		DelegateCrud<ProfissionalSaude, Long, ProfissionalSaudeDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;

	public ProfissionalSaude buscarAtivo(Usuario usuario) {

		try {
			return getDelegate().buscarAtivo(usuario);
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public ProfissionalSaude update(ProfissionalSaude profissional) {

		usuarioBC.update(profissional.getUsuario());
		return super.update(profissional);
	}

	@Override
	public ProfissionalSaude insert(ProfissionalSaude profissional) {

		Usuario usuario = profissional.getUsuario();

		if (Util.isNotNull(usuario.getId())) {
			usuario = usuarioBC.load(usuario.getId());
		}

		profissional.setUsuario(usuario);

		profissional.getUsuario().addPerfil(Perfil.PROFISSIONAL_SAUDE);
		profissional.setStatus(StatusRegistro.ATIVO);

		return super.insert(profissional);
	}

	public List<ProfissionalSaude> pesquisar(ProfissionalSaude profissional) {

		return getDelegate().pesquisar(profissional);
	}

	@Transactional
	public void reativar(ProfissionalSaude profissionalSaude) {

		profissionalSaude.setStatus(StatusRegistro.ATIVO);
		update(profissionalSaude);

		Usuario usuario = usuarioBC
				.load(profissionalSaude.getUsuario().getId());
		usuario.addPerfil(Perfil.PROFISSIONAL_SAUDE);
		usuarioBC.update(usuario);

	}

	@Transactional
	public void inativar(ProfissionalSaude profissionalSaude) {

		profissionalSaude.setStatus(StatusRegistro.INATIVO);
		update(profissionalSaude);

		Usuario usuario = usuarioBC
				.load(profissionalSaude.getUsuario().getId());

		usuario.removePerfil(Perfil.PROFISSIONAL_SAUDE);
		usuarioBC.update(usuario);
	}

}
