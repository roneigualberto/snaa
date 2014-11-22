package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.persistence.MunicipioDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class MunicipioBC extends DelegateCrud<Municipio, Long, MunicipioDAO> {

	private static final long serialVersionUID = 1L;

	private static final String NOME_AMAZONAS = "AMAZONAS";

	public List<Municipio> buscarPorEstado(Estado estado) {

		return getDelegate().buscarPorEstado(estado);
	}

	public List<Municipio> obterMunicipioDoAmazonas() {

		return getDelegate().buscarPorNomeEstado(NOME_AMAZONAS);
	}

}
