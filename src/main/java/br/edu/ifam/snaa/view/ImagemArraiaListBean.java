package br.edu.ifam.snaa.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.edu.ifam.snaa.business.ImagemArraiaBC;
import br.edu.ifam.snaa.domain.notificacao.ImagemArraia;

@ViewController
@NextView("./ImagemArraia_edit.jsf")
@PreviousView("./ImagemArraia_list.jsf")
public class ImagemArraiaListBean extends AbstractListPageBean<ImagemArraia, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ImagemArraiaBC imagemArraiaBC;
	
	@Override
	protected List<ImagemArraia> handleResultList() {
		return this.imagemArraiaBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				imagemArraiaBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
	
	
	


}