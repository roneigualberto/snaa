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
import br.edu.ifam.snaa.business.VitimaBC;
import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.gov.frameworkdemoiselle.util.Beans;

@FacesValidator("vitimaPassaporteExistente")
public class VitimaPassaporteExistenteValidator implements Validator {

	private VitimaBC vitimaBC = Beans.getReference(VitimaBC.class);

	private static final String KEY_ID_VITIMA = "idVitima";


	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;

		try {
			String passaporte = value.toString();
			Long idVitima = (Long) component.getAttributes().get(KEY_ID_VITIMA);


			Vitima vitima = vitimaBC.buscarPorPassaporte(passaporte);

			if (vitima != null && !vitima.getId().equals(idVitima)) {
				throw new ValidatorException(new FacesMessage(severity,
						"Passaporte já existe no sistema", null));
			}

		} catch (InvalidStateException e) {
			throw new ValidatorException(new FacesMessage(severity,
					"Cpf inválido", null));
		}

	}

}
