package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.domain.endereco.TipoLogradouro;

@RequestScoped
@Named
public class TipoLogradouroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;

	public TipoLogradouro[] getList() {

		return TipoLogradouro.values();
	}

}
