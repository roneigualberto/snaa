package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifam.snaa.security.Credenciais;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.Controller;

@RequestScoped
@Named
@Controller
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	@Inject
	private Credenciais credenciais;

	@Inject
	private SecurityContext securityContext;

	@Inject
	private JSFUtil jsfUtil;

	public String efetuarLogin() {
		try {
			securityContext.login();

		} catch (AuthenticationException e) {
			jsfUtil.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
			return "";
		}

		return "/perfil";

	}

	public Credenciais getCredenciais() {
		return credenciais;
	}

	public void tratarErroAutenticacao(AuthenticationException ex) {

	}

}
