package br.edu.ifam.snaa.factory;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.ImagemArraiaBC;
import br.edu.ifam.snaa.business.ImagemController;
import br.gov.frameworkdemoiselle.stereotype.Controller;

@Controller
public class ImagemArraiaController implements ImagemController {

	@Inject
	private ImagemArraiaBC imagemArraiaBC;

	@Override
	public byte[] loadImage(Long id) {
		return imagemArraiaBC.getBytes(id);
	}

	@Override
	public boolean hasPermission() {
		return true;
	}

}
