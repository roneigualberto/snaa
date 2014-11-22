package br.edu.ifam.snaa.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.ArraiaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.Arraia;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView(value = "/pages/administrativo/cadastros/arraia/arraiaForm.jsf")
@PreviousView(value = "/pages/administrativo/cadastros/arraia/arraiaList.jsf")
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class ArraiaListBean extends AbstractListPageBean<Arraia, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ArraiaBC arraiaBC;

	@Inject
	private Arraia arraia;

	private List<Arraia> arraias;

	@PostConstruct
	public void init() {

		pesquisar();
	}

	@Override
	protected List<Arraia> handleResultList() {
		return this.arraias;
	}

	public void pesquisar() {
		this.clear();
		this.arraias = arraiaBC.pesquisar(arraia);
	}

	public Arraia getArraia() {
		return arraia;
	}

}