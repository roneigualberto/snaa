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
import br.edu.ifam.snaa.business.RepresentanteUnidadeSaudeBC;
import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.gov.frameworkdemoiselle.util.Beans;

@FacesValidator("representanteCpfExistente")
public class RepresentanteCpfExistenteValidator implements Validator {

	private RepresentanteUnidadeSaudeBC representanteBC = Beans.getReference(RepresentanteUnidadeSaudeBC.class);

	private static final String KEY_ID_REPRESENTANTE = "idVitima";

	private CPFValidator cpfValidator = new CPFValidator(false);

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;

		try {
			String cpf = value.toString();
			Long idRepresentante = (Long) component.getAttributes().get(KEY_ID_REPRESENTANTE);


			RepresentanteUnidadeSaude representante = representanteBC.buscarPorCPF(cpf);

			if (representante != null && !representante.getId().equals(idRepresentante)) {
				throw new ValidatorException(new FacesMessage(severity,
						"Cpf já existe no sistema", null));
			}

		} catch (InvalidStateException e) {
			throw new ValidatorException(new FacesMessage(severity,
					"Cpf inválido", null));
		}

	}

}
