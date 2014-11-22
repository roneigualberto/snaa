package br.edu.ifam.snaa.business;

import java.util.List;

import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.edu.ifam.snaa.persistence.VitimaDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class VitimaBC extends DelegateCrud<Vitima, Long, VitimaDAO> {

	private static final long serialVersionUID = 1L;

	public List<Vitima> listar() {
		return getDelegate().findAll();
	}

	public List<Vitima> pesquisar(Vitima vitima) {

		return getDelegate().pesquisar(vitima);
	}

	public Vitima buscarPorCPF(String cpf) {
		return getDelegate().buscarPorCPF(cpf);
	}
	
	public Vitima buscarPorPassaporte(String passaporte) {
		return getDelegate().buscarPorPassaporte(passaporte);
	}

}
