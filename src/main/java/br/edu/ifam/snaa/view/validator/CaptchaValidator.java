package br.edu.ifam.snaa.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

@FacesValidator("captcha")
public class CaptchaValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		Captcha secretcaptcha = (Captcha) session.getAttribute(Captcha.NAME);
		
		((HtmlInputText) component).setSubmittedValue("");
		
		if (secretcaptcha.isCorrect(value.toString())) {
			return;
		}
			

		((HtmlInputText) component).setSubmittedValue("");	

		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Código de verificação inválido", "Código de verificação inválido"));

	}

}
