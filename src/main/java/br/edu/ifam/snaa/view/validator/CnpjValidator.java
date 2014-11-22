package br.edu.ifam.snaa.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@FacesValidator("cnpj")
public class CnpjValidator implements Validator {

	private CNPJValidator cnpjValidator = new CNPJValidator(false);

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;

		try {
			String cnpj = value.toString();
			cnpjValidator.assertValid(cnpj);
		} catch (InvalidStateException e) {
			throw new ValidatorException(new FacesMessage(severity,
					"CNPJ inválido", null));
		}

	}

}
