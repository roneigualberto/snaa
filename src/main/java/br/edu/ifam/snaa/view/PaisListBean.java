package br.edu.ifam.snaa.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.PaisBC;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@NextView("/pages/administrativo/cadastros/endereco/pais/paisForm.jsf")
@PreviousView("/pages/administrativo/cadastros/endereco/pais/paisList.jsf")
public class PaisListBean extends AbstractListPageBean<Pais, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private PaisBC paisBC;

	@Inject
	private Pais pais;

	private Pais paisAtivacao;

	@Inject
	private MessageContext messageContext;

	private List<Pais> paises;

	@PostConstruct
	public void init() {

		pesquisar();
	}

	@Override
	protected List<Pais> handleResultList() {
		return this.paises;
	}

	public List<Pais> buscar(String nome) {

		return paisBC.buscarPorNome(nome);

	}

	public Pais getPais() {
		return pais;
	}

	public void pesquisar() {
		this.paises = this.paisBC.pesquisar(pais);
	}

	@Transactional
	public String reativar() {
		this.paisAtivacao.setStatus(StatusRegistro.ATIVO);

		this.paisBC.update(paisAtivacao);

		messageContext.add("Registro reativado com sucesso", SeverityType.INFO);

		return getPreviousView();

	}

	@Transactional
	public String inativar() {
		this.paisAtivacao.setStatus(StatusRegistro.INATIVO);

		this.paisBC.update(paisAtivacao);

		messageContext
				.add("Registro inativador com sucesso", SeverityType.INFO);

		return getPreviousView();

	}

	public Pais getPaisAtivacao() {
		return paisAtivacao;
	}

	public void setPaisAtivacao(Pais paisAtivacao) {
		this.paisAtivacao = paisAtivacao;
	}

	public List<Pais> getPaises() {
		return paises;
	}
}