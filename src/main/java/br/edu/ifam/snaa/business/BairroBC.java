package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.endereco.Bairro;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.persistence.BairroDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class BairroBC extends DelegateCrud<Bairro, Long, BairroDAO> {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public List<Bairro> buscarPorMunicipio(Municipio municipio) {
		
		return getDelegate().buscarPorMunicipio(municipio);
	}
	
}
