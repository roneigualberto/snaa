package br.edu.ifam.snaa.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.util.ReflectionUtil;
import br.edu.ifam.snaa.util.Util;

@FacesConverter(value = "localizacaoConverter")
public class LocalizacaoConverter extends EntityConverter {
	

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
			return new Localizacao(value);
		}
		
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		String retorno = null;

		try {
			Long id = ReflectionUtil.getValueField(value, "id",
					Long.class);

			retorno = (id != null ? id.toString() : ReflectionUtil
					.getValueField(value, "descricao", String.class));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;

	}
	
	

}
