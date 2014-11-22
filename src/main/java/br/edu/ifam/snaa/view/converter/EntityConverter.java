package br.edu.ifam.snaa.view.converter;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import br.gov.frameworkdemoiselle.util.Beans;

@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	@Inject
	private EntityManager entityManager = Beans
			.getReference(EntityManager.class);
	
	
	protected  EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		try {
			Class<?> clazz = component.getValueExpression("value").getType(
					context.getELContext());
			
			/*if (value == null || "0".equals(value)) {
				return null;
			}*/

			Long id = Long.valueOf(value);

			return entityManager.find(clazz, id);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		
		Class<?> clazz = getClassComponent(context, component);

		Object id = null;
		Field[] fields = clazz.getDeclaredFields();

		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(Id.class)) {
					field.setAccessible(true);
					id = field.get(value);
					break;
				}
			}
		} catch (Exception e) {
		}

		return String.valueOf(id);
	}
	
	protected Class<?> getClassComponent(FacesContext context,UIComponent component) {
		Class<?> clazz = component.getValueExpression("value").getType(
				context.getELContext());
		
		return clazz;
	}

}
