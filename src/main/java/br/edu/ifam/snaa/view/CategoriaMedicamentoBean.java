package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.CategoriaMedicamento;
import br.gov.frameworkdemoiselle.security.RequiredRole;

@RequestScoped
@Named
@RequiredRole(Constantes.PERFIL_PROFISSIONAL_SAUDE)
public class CategoriaMedicamentoBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867126307179446870L;
	
	
	
	
	public CategoriaMedicamento[] getList() {
		
		return CategoriaMedicamento.values();
	}

}
