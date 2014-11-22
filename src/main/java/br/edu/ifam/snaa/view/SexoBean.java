package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.domain.SexoEnum;

@RequestScoped
@Named
public class SexoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	/**
	 * 
	 * @return Retorna o enum de sexo para as views
	 */
	public SexoEnum[] getList() {

		return SexoEnum.values();
	}

}
