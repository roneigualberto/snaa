package br.edu.ifam.snaa.business;

import javax.inject.Inject;

import br.edu.ifam.snaa.domain.endereco.Endereco;
import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.domain.endereco.Logradouro;
import br.edu.ifam.snaa.persistence.EnderecoDAO;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class EnderecoBC extends DelegateCrud<Endereco, Long, EnderecoDAO> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LogradouroBC logradouroBC;

	@Override
	public Endereco insert(Endereco endereco) {

		Localizacao localizacao = endereco.getLocalizacao();

		if (localizacao == null || Util.isNull(localizacao.getDescricao())) {
			endereco.setLocalizacao(null);
		}
		
		Logradouro logradouro = this.logradouroBC.load(endereco.getLogradouro().getId());
		
		endereco.setLogradouro(logradouro);

		return super.insert(endereco);
	}

}
