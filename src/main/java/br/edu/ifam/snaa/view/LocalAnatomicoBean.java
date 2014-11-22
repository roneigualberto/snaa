package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class LocalAnatomicoBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;
	
	
	
	
	public LocalAnatomicoBean test(LocalAnatomicoBean localAnatomicoBean) {
		
		return localAnatomicoBean;
	}

}
