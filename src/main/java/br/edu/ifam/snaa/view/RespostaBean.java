package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.util.RespostaEnum;

@RequestScoped
@Named
public class RespostaBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	public RespostaEnum[] getList() {
		
		return RespostaEnum.values();
	}

}
