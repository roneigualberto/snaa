package br.edu.ifam.snaa.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifam.snaa.business.AdministradorBC;
import br.edu.ifam.snaa.business.ProfissionalSaudeBC;
import br.edu.ifam.snaa.business.RepresentanteUnidadeSaudeBC;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.Administrador;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;
import br.edu.ifam.snaa.domain.seguranca.Perfil;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.gov.frameworkdemoiselle.security.AuthorizationException;
import br.gov.frameworkdemoiselle.security.NotLoggedInException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.security.User;

@SessionScoped
@Named
public class UsuarioSessao implements Serializable, User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7545010919042687865L;

	public static final String KEY_USUARIO_SESSAO = "USUARIO_SESSAO";

	private Usuario usuario;

	@Inject
	private SecurityContext securityContext;

	@Inject
	private ProfissionalSaudeBC profissionalSaudeBC;

	@Inject
	private RepresentanteUnidadeSaudeBC representanteBC;

	@Inject
	private AdministradorBC administradorBC;

	private Map<Perfil, Object> mapPerfil = new HashMap<Perfil, Object>();

	public void carregarPerfis(Usuario usuario) {
		this.usuario = usuario;

		if (!StatusRegistro.ATIVO.equals(usuario.getStatus())) {
			throw new AuthorizationException("Usuário não esta ativo");
			
		}

		if (usuario.temPerfil(Perfil.PROFISSIONAL_SAUDE)) {
			ProfissionalSaude profissionalSaude = profissionalSaudeBC
					.buscarAtivo(usuario);
			
			if (profissionalSaude  != null) {
				mapPerfil.put(Perfil.PROFISSIONAL_SAUDE, profissionalSaude);
			}

			
		}

		if (usuario.temPerfil(Perfil.REPRESENTANTE_UNIDADE_SAUDE)) {
			RepresentanteUnidadeSaude representanteUnidadeSaude = representanteBC
					.buscarAtivo(usuario);
			
			if (representanteUnidadeSaude != null) {
				mapPerfil.put(Perfil.REPRESENTANTE_UNIDADE_SAUDE,
						representanteUnidadeSaude);
			}

			
		}

		if (usuario.temPerfil(Perfil.ADMINISTRADOR)) {

			Administrador administrador = this.administradorBC
					.buscarAtivo(usuario);
			
			if (administrador != null) {
				mapPerfil.put(Perfil.ADMINISTRADOR, administrador);
			}

			
		}

		if (mapPerfil.isEmpty()) {
			throw new AuthorizationException("Usuário não esta ativo");
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {

		return (this.usuario != null);
	}

	public String logout() {

		try {
			securityContext.logout();
		} catch (NotLoggedInException ex) {
			ex.printStackTrace();
		}

		return "/login/login";

	}

	public ProfissionalSaude getProfissionalSaude() {
		if (!mapPerfil.containsKey(Perfil.PROFISSIONAL_SAUDE))
			return null;
		return (ProfissionalSaude) mapPerfil.get(Perfil.PROFISSIONAL_SAUDE);
	}

	public UnidadeSaude getUnidadeSaude() {

		return getRepresentanteUnidadeSaude() == null ? null : getRepresentanteUnidadeSaude().getUnidadeSaude();
	}

	public RepresentanteUnidadeSaude getRepresentanteUnidadeSaude() {
		if (!mapPerfil.containsKey(Perfil.REPRESENTANTE_UNIDADE_SAUDE))
			return null;
		return (RepresentanteUnidadeSaude) mapPerfil
				.get(Perfil.REPRESENTANTE_UNIDADE_SAUDE);
	}

	public Administrador getAdministrador() {
		if (!mapPerfil.containsKey(Perfil.ADMINISTRADOR))
			return null;
		return (Administrador) mapPerfil.get(Perfil.ADMINISTRADOR);
	}

	@Override
	public String getId() {

		return String.valueOf(usuario == null ? "" : usuario.getId());
	}

	@Override
	public Object getAttribute(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(Object key, Object value) {
		// TODO Auto-generated method stub

	}

	public boolean temPerfil(Perfil perfil) {
		return this.usuario.temPerfil(perfil) && mapPerfil.containsKey(perfil);
	}

}
