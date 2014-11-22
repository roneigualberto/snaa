package br.edu.ifam.snaa.business;

import java.util.List;

import javax.persistence.NoResultException;

import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.persistence.PaisDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class PaisBC extends DelegateCrud<Pais, Long, PaisDAO> {

	private static final long serialVersionUID = 1L;
	
	private static final String PAIS_BRASIL="Brasil";

	public List<Pais> buscarPorNome(String descricao) {

		return getDelegate().buscarPorNome(descricao);

	}

	public Pais buscarPorNomeIdentico(String nome) {
		try {
			return getDelegate().buscarPorNomeIdentico(nome);
		} catch (NoResultException e) {
			return null;
		}

	}

	public Pais buscarPorSiglaIdentico(String sigla) {
		try {
			return getDelegate().buscarPorSiglaIdentica(sigla);
		} catch (NoResultException e) {
			return null;
		}

	}
	
	public List<Pais> pesquisar(Pais pais) {
		return  getDelegate().pesquisar(pais);
	}

	public Pais obterPaisBrasil() {
		
		
		return this.getDelegate().buscarPorNomeIdentico(PAIS_BRASIL);
	}

}
