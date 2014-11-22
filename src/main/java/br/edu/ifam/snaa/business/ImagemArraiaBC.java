package br.edu.ifam.snaa.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.edu.ifam.snaa.domain.notificacao.ImagemArraia;
import br.edu.ifam.snaa.persistence.ImagemArraiaDAO;

@BusinessController
public class ImagemArraiaBC extends DelegateCrud<ImagemArraia, Long, ImagemArraiaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public byte[] getBytes(Long id) {
		 
		
		ImagemArraia imagemArraia = load(id);
		
		return imagemArraia.getArquivo().getImagem();
	}
	
}
