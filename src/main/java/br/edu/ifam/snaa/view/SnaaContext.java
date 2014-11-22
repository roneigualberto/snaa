package br.edu.ifam.snaa.view;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifam.snaa.business.EstadoBC;
import br.edu.ifam.snaa.business.PaisBC;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.gov.frameworkdemoiselle.lifecycle.Startup;

/****
 * 
 * 
 * @author Ronei
 * 
 *         Classe responsável por carregar valores padrões de configuração do
 *         projeto
 * 
 */

@ApplicationScoped
@Named
public class SnaaContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PaisBC paisBC;

	@Inject
	private EstadoBC estadoBC;

	private Estado estado;

	private Pais pais;

	@Startup
	public void init() {

		pais = this.paisBC.obterPaisBrasil();
		estado = this.estadoBC.obterEstadoDoAmazonas();

	}

	public Pais getPais() {
		return pais;
	}

	public Estado getEstado() {
		return estado;
	}

}
