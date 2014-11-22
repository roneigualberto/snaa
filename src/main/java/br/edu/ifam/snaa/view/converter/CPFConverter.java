package br.edu.ifam.snaa.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.caelum.stella.format.CPFFormatter;
import br.edu.ifam.snaa.util.Util;

@FacesConverter("cpf")
public class CPFConverter implements Converter {
	
	private static final CPFFormatter CPF_FORMATTER = new CPFFormatter();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		try {
			if (Util.isNull(value)) {
				return null;
			}
			return CPF_FORMATTER.unformat(value);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		String valor = (value == null ? null : value.toString());
		
		if (Util.isNull(valor)) {
			return "";
		}
			
		return CPF_FORMATTER.format(valor);
	}

}
