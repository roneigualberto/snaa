package br.edu.ifam.snaa.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.MunicipioBC;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
public class MunicipioListBean extends AbstractListPageBean<Municipio, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MunicipioBC municipioBC;

	private List<Municipio> municipiosDoAmazonas;

	@Override
	protected List<Municipio> handleResultList() {
		return this.municipioBC.findAll();
	}

	public List<Municipio> getMunicipiosDoAmazonas() {

		if (this.municipiosDoAmazonas == null) {
			this.municipiosDoAmazonas = this.municipioBC
					.obterMunicipioDoAmazonas();
		}
		
		return this.municipiosDoAmazonas;
	}

}