package br.edu.ifam.snaa.view;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.EscolaridadeBC;
import br.edu.ifam.snaa.domain.notificacao.Escolaridade;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@NextView("/pages/administrativo/cadastros/escolaridade/escolaridadeForm.jsf")
@PreviousView("/pages/administrativo/cadastros/escolaridade/escolaridadeList.jsf")
public class EscolaridadeListBean extends AbstractListPageBean<Escolaridade, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EscolaridadeBC escolaridadeBC;
	
	@Inject
	private Escolaridade escolaridade;
	
	private List<Escolaridade> escolaridades;
	
	@Override
	protected List<Escolaridade> handleResultList() {
		if (escolaridades == null) {
			this.escolaridades = this.escolaridadeBC.findAll();
		}
		return escolaridades;
	}
	
	public void pesquisar(Escolaridade escolaridade) {
		this.escolaridades = this.escolaridadeBC.pesquisar(escolaridade);
	}
	
	
	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

}