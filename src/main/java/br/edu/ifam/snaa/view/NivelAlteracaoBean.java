package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.domain.notificacao.oficial.NivelAlteracaoEnum;

@RequestScoped
@Named
public class NivelAlteracaoBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	public NivelAlteracaoEnum[] getList() {
		
		return NivelAlteracaoEnum.values();
	}

}
