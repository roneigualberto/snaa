package br.edu.ifam.snaa.business;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.config.SnaaConfig;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.persistence.EstadoDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class EstadoBC extends DelegateCrud<Estado, Long, EstadoDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SnaaConfig config;

	public List<Estado> pesquisar(Estado estado) {

		return getDelegate().pesquisar(estado);
	}

	

	public List<Estado> buscarPorPais(Pais pais) {
		return getDelegate().buscarPorPais(pais);
	}

	public Estado obterEstadoDoAmazonas() {
		return getDelegate().buscarPorNome(config.getEstado());
	}

}
