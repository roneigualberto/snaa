package br.edu.ifam.snaa.view;

import static br.edu.ifam.snaa.constant.Constantes.PERFIL_ADMINISTRADOR;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.AdministradorBC;
import br.edu.ifam.snaa.business.UsuarioBC;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.Administrador;
import br.edu.ifam.snaa.domain.seguranca.Perfil;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.util.JSFUtil;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@PreviousView("/pages/administrativo/cadastros/administrador/administradorList.jsf")
@ViewController
@RequiredRole(value = PERFIL_ADMINISTRADOR)
public class AdministradorBean extends
		AbstractEditPageBean<Administrador, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478083143888312562L;

	@Inject
	private AdministradorBC administradorBC;

	@Inject
	private UsuarioBC usuarioBC;

	private String confirmacaoSenha;


	@Inject
	private MessageContext messageContext;
	
	@Inject
	private JSFUtil jsfUtil;

	
	@Override
	public String delete() {
		return null;
	}

	@Override
	@Transactional
	public String insert() {
		getBean().setStatus(StatusRegistro.ATIVO);
		this.administradorBC.insert(getBean());
		
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(Administrador.class));

		return null;
	}

	@Override
	@Transactional
	public String update() {
		this.administradorBC.update(getBean());
		
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		return null;
	}

	@Override
	protected Administrador handleLoad(Long id) {
		return this.administradorBC.load(id);
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public void handleBuscarUsuario(AjaxBehaviorEvent event) {

		String cpf = getBean().getUsuario().getCpf();
		Usuario usuarioEncontrado = usuarioBC.buscarPorCPF(cpf);

		if (usuarioEncontrado != null) {
			getBean().setUsuario(usuarioEncontrado);
			messageContext
					.add("Já existe um usuário com cpf informado: os dados não podem ser editados",
							SeverityType.WARN);
		}

	}

	public void validateCpf(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		String cpf = value.toString();
		Usuario usuarioEncontrado = usuarioBC.buscarPorCPF(cpf);

		if (usuarioEncontrado != null
				&& usuarioEncontrado.temPerfil(Perfil.ADMINISTRADOR)) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Usuario ja existe para o perfil de administrador",
					null));
		}

	}

	public boolean isExisteUsuario() {

		return Util.isNotNull(getBean().getUsuario().getId());
	}

}
