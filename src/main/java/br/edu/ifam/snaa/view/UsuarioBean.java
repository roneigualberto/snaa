package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifam.snaa.business.EmailBC;
import br.edu.ifam.snaa.business.UsuarioBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.security.UsuarioSessao;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.Controller;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

@RequestScoped
@Named
@Controller
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	@Inject
	private UsuarioSessao usuarioSessao;

	@Inject
	private UsuarioBC usuarioBC;

	@Inject
	private EmailBC emailBC;

	private String senhaAtual;
	private String novaSenha;
	private String confirmacaoSenha;
	private String cpf;

	@Inject
	private JSFUtil jsfUtil;

	@Inject
	private MessageContext messageContext;

	@Inject
	private ResourceBundle bundle;

	@Transactional
	@RequiredRole({ Constantes.PERFIL_ADMINISTRADOR,
		Constantes.PERFIL_PROFISSIONAL_SAUDE,
		Constantes.PERFIL_REPRESENTANTE_UNIDADE_DE_SAUDE })
	public void alterarSenha() {

		Long idUsuario = this.usuarioSessao.getUsuario().getId();
		Usuario usuario = this.usuarioBC.load(idUsuario);

		if (!usuario.getSenha().equals(senhaAtual)) {
			jsfUtil.addMessage("senha.atual.invalida");
			return;
		}

		if (!novaSenha.equals(confirmacaoSenha)) {
			jsfUtil.addMessage("senha.confirmacao");
			return;
		}

		usuario.setSenha(novaSenha);

		this.usuarioBC.update(usuario);

		jsfUtil.addMessage(SeverityType.INFO, "alteracao.senha.sucesso");

	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	@Transactional
	public void recuperarSenha() {
		Usuario usuario = this.usuarioBC.buscarPorCPF(cpf);
		String value = "Usuário não existe no sistema";
		if (usuario == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					value, null);
			FacesContext.getCurrentInstance().addMessage(
					"tabView:formulario:localAnatomico", msg);

			return;
		}

		this.emailBC.enviarSenha(usuario);

		String valor = bundle.getString("email.recuperacao.sucesso",
				usuario.getEmail());
		this.messageContext.add(valor, SeverityType.INFO);

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
