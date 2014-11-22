package br.edu.ifam.snaa.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.EstadoBC;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@NextView("/pages/administrativo/cadastros/endereco/estado/estadoForm.jsf")
@PreviousView("/pages/administrativo/cadastros/endereco/estado/estadoList.jsf")
public class EstadoListBean extends AbstractListPageBean<Estado, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoBC estadoBC;

	@Inject
	private Estado estado;

	private List<Estado> paises;

	@PostConstruct
	public void init() {

		pesquisar();
	}

	@Override
	protected List<Estado> handleResultList() {
		return this.paises;
	}

	public Estado getEstado() {
		return estado;
	}

	public void pesquisar() {
		this.clear();
		this.paises = this.estadoBC.pesquisar(estado);
	}

	public List<Estado> getEstados() {
		return paises;
	}
}