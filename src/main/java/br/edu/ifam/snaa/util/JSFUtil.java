package br.edu.ifam.snaa.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;

@Named
@RequestScoped
public class JSFUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7551631591427438704L;
	@Inject
	private FacesContext facesContext;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private MessageContext messageContext;

	@Inject
	private Logger logger;

	public void addMessage(SeverityType type, String key) {

		String value = bundle.getString(key);

		logger.info(value);
		messageContext.add(value, type);

	}
	
	public String getMessage(String key) {
		return bundle.getString(key);
	}

	public void addMessage(Severity severity, String key) {
		String bundleName = "messages";

		Locale locale = facesContext.getViewRoot().getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale);

		String mensagem = bundle.getString(key);

		facesContext.addMessage(null,
				new FacesMessage(severity, mensagem, null));

	}
	
	

	public void addMessage(String key) {
		addMessage(FacesMessage.SEVERITY_ERROR, key);
	}

}
