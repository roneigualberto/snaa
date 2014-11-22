package br.edu.ifam.snaa.view.converter;

import java.lang.reflect.Constructor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import br.edu.ifam.snaa.util.ReflectionUtil;
import br.edu.ifam.snaa.util.Util;

@FacesConverter(value = "optionalAutoComplete")
public class OptionalAutoCompleteConverter extends EntityConverter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || "null".equals(value)) {
			return null;
		}

		Object entity = null;

		try {
			Long id = Util.parseLong(value);

			if (id != null) {
				entity = super.getAsObject(context, component, value);
			} else {
				Class<?> classComponent = getClassComponent(context, component);

				Constructor<?> constructor = classComponent
						.getConstructor(String.class);
				entity = constructor.newInstance(value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;

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
