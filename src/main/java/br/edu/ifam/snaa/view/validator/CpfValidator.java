package br.edu.ifam.snaa.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.util.Beans;

@FacesValidator("cpf")
public class CpfValidator implements Validator {

	private CPFValidator validator = new CPFValidator(false);

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;

		try {
			String cpf = value.toString();
			validator.assertValid(cpf);
		} catch (InvalidStateException e) {
			JSFUtil jsfUtil = Beans.getReference(JSFUtil.class);

			throw new ValidatorException(new FacesMessage(severity,
					jsfUtil.getMessage("cpf.invalido"), null));
		}

	}

}
