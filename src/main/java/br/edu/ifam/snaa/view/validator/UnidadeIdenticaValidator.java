package br.edu.ifam.snaa.view.validator;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.ifam.snaa.business.UnidadeSaudeBC;
import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;
import br.gov.frameworkdemoiselle.util.Beans;

@FacesValidator("unidadeExistente")
public class UnidadeIdenticaValidator implements Validator {

	private UnidadeSaudeBC unidadeBC = Beans.getReference(UnidadeSaudeBC.class);

	private static final String KEY_ID_UNIDADE = "idUnidade";


	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;


		String codigo = value.toString();
		Long idUnidade = (Long) component.getAttributes().get(KEY_ID_UNIDADE);

		UnidadeSaude unidade = unidadeBC.buscarPorCodigo(codigo);

		if (unidade != null && !unidade.getId().equals(idUnidade)) {
			throw new ValidatorException(new FacesMessage(severity,
					"Codigo de unidade  já existe no sistema", null));
		}

	}

}
