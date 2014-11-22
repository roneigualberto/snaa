package br.edu.ifam.snaa.business;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.validator.engine.ConstraintViolationImpl;

import br.edu.ifam.snaa.domain.notificacao.Arraia;
import br.edu.ifam.snaa.persistence.ArraiaDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class ArraiaBC extends DelegateCrud<Arraia, Long, ArraiaDAO> {

	private static final long serialVersionUID = 1L;

	public List<Arraia> pesquisar(Arraia arraia) {

		if (arraia == null) {
			throw new IllegalArgumentException("não pode ser nulo");
		}

		return getDelegate().pesquisar(arraia);
	}

	public Arraia buscarPorNomeIdentico(String nome) {
		try {
			return getDelegate().buscarPorNomeIdentico(nome);
		} catch (NoResultException e) {
			return null;
		}
		
	}

	public Arraia buscarPorNomeCientificoIdentico(String nome) {
		
		try {
			return getDelegate().buscarPorNomeCientificoIdentico(nome);
		
		
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
		

}
