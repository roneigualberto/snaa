package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.domain.notificacao.oficial.SituacaoAnimal;

@RequestScoped
@Named
public class SituacaoAnimalBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	public SituacaoAnimal[] getList() {
		
		return SituacaoAnimal.values();
	}

}