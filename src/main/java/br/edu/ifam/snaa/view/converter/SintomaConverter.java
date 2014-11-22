package br.edu.ifam.snaa.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifam.snaa.domain.notificacao.oficial.Sintoma;
import br.edu.ifam.snaa.util.Util;

@FacesConverter(value = "sintomaConverter")
public class SintomaConverter extends EntityConverter {
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		
		if (value == null || "null".equals(value)) {
			return null;
		}
				
		Long id = Util.parseLong(value);
		
		if (id != null) {
			return super.getAsObject(context, component, value);
		}
		else {
			return new Sintoma(value);
		}
		
	}
	
	

}
