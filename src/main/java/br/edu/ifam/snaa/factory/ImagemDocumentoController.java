package br.edu.ifam.snaa.factory;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.ImagemController;
import br.edu.ifam.snaa.business.SolicitacaoContaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.gov.frameworkdemoiselle.security.SecurityContext;
import br.gov.frameworkdemoiselle.stereotype.Controller;

@Controller
public class ImagemDocumentoController implements ImagemController {

	@Inject
	private SolicitacaoContaBC solicitacaoContaBC;

	@Inject
	private SecurityContext securityContext;

	@Override
	public byte[] loadImage(Long id) {
		return solicitacaoContaBC.load(id).getRepresentante().getDocumento()
					.getArquivo();
	}

	@Override
	public boolean hasPermission() {
		return securityContext.hasRole(Constantes.PERFIL_ADMINISTRADOR);
	}

}
