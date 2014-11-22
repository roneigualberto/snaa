package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.domain.notificacao.TipoNotificacaoEnum;

@RequestScoped
@Named
public class TipoNotificacaoBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	public TipoNotificacaoEnum[] getList() {
		
		return TipoNotificacaoEnum.values();
	}

}
