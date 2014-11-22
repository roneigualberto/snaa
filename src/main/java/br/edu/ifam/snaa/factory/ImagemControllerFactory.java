package br.edu.ifam.snaa.factory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;

import br.edu.ifam.snaa.business.ImagemController;
import br.gov.frameworkdemoiselle.util.Beans;

@Named
@Singleton
public class ImagemControllerFactory {

	private static Map<String, Class<? extends ImagemController>> map;

	static {
		map = new HashMap<String, Class<? extends ImagemController>>();
		map.put("arraia", ImagemArraiaController.class);
		map.put("documento", ImagemDocumentoController.class);
	}

	public ImagemController create(String context) {

		Class<? extends ImagemController> clazz = map.get(context);

		return Beans.getReference(clazz);

	}
}
