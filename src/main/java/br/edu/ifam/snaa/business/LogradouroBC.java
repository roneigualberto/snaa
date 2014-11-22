package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.endereco.Logradouro;
import br.edu.ifam.snaa.persistence.LogradouroDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class LogradouroBC extends DelegateCrud<Logradouro, Long, LogradouroDAO> {

	private static final long serialVersionUID = 1L;

	public List<Logradouro> buscar(Logradouro logradouro) {

		return getDelegate().buscar(logradouro);
	}

}
