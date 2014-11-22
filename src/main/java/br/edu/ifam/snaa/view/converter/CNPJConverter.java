package br.edu.ifam.snaa.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.caelum.stella.format.CNPJFormatter;
import br.edu.ifam.snaa.util.Util;

@FacesConverter("cnpj")
public class CNPJConverter implements Converter {

	private static final CNPJFormatter CNPJ_FORMATTER = new CNPJFormatter();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (Util.isNull(value)) {
			return null;
		}
		return CNPJ_FORMATTER.unformat(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		String valor = (value == null ? null : value.toString());

		if (Util.isNull(valor)) {
			return "";
		}

		return CNPJ_FORMATTER.format(valor);
	}

}
