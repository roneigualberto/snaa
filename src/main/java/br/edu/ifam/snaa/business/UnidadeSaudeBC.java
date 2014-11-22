package br.edu.ifam.snaa.business;

import javax.inject.Inject;

import br.edu.ifam.snaa.domain.endereco.Endereco;
import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;
import br.edu.ifam.snaa.persistence.UnidadeSaudeDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class UnidadeSaudeBC extends
		DelegateCrud<UnidadeSaude, Long, UnidadeSaudeDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoBC enderecoBC;

	@Inject
	private RepresentanteUnidadeSaudeBC representanteBC;

	public UnidadeSaude buscarPorCodigo(String codigo) {
		return getDelegate().buscarPorCodigo(codigo);
	}

	@Override
	public UnidadeSaude insert(UnidadeSaude unidade) {

		Endereco endereco = enderecoBC.insert(unidade.getEndereco());
		RepresentanteUnidadeSaude representante = unidade.getRepresentante();


		representante = representanteBC.insert(representante);

		unidade.setEndereco(endereco);
		unidade.setRepresentante(representante);

		return super.insert(unidade);
	}

}
