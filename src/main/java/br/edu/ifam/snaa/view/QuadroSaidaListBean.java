package br.edu.ifam.snaa.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.QuadroSaidaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.QuadroSaida;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@NextView("/pages/administrativo/cadastros/quadrosaida/quadrosaidaForm.jsf")
@PreviousView("/pages/administrativo/cadastros/quadrosaida/quadrosaidaList.jsf")
@RequiredRole({Constantes.PERFIL_PROFISSIONAL_SAUDE,Constantes.PERFIL_ADMINISTRADOR})
public class QuadroSaidaListBean extends AbstractListPageBean<QuadroSaida, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuadroSaidaBC quadroSaidaBC;
	
	@Inject
	private QuadroSaida quadroSaida;
	
	private List<QuadroSaida> quadroSaidas;
	
	
	
	@Override
	protected List<QuadroSaida> handleResultList() {
		
		if (quadroSaidas == null) {
			this.quadroSaidas = this.quadroSaidaBC.findAll();
		}
		return  this.quadroSaidas;
	}
	
	public void pesquisar() {
		this.quadroSaidas = this.quadroSaidaBC.pesquisar(quadroSaida);
		
	}
	
	
	
	public QuadroSaida getQuadroSaida() {
		return quadroSaida;
	}
	


}