package br.edu.ifam.snaa.view;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import br.edu.ifam.snaa.business.PaisBC;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
@PreviousView("/pages/administrativo/cadastros/endereco/pais/paisList.jsf")
public class PaisBean extends AbstractEditPageBean<Pais, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private PaisBC paisBC;

	@Inject
	private JSFUtil jsfUtil;

	@Override
	@Transactional
	public String delete() {
		this.paisBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		this.paisBC.insert(getBean());

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(Beans.getReference(Pais.class));
		return null;
	}

	@Override
	@Transactional
	public String update() {
		this.paisBC.update(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		return null;
	}

	@Override
	protected Pais handleLoad(Long id) {
		return this.paisBC.load(id);
	}

	public void validateNome(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;
		String nome = value.toString();

		Pais paisIdentico = this.paisBC.buscarPorNomeIdentico(nome);

		if (paisIdentico != null && !paisIdentico.equals(getBean())) {
			throw new ValidatorException(new FacesMessage(severity,
					"Nome informado já existe no sistema", null));
		}

	}

	public void validateSigla(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;
		String sigla = value.toString();

		Pais paisIdentico = this.paisBC.buscarPorSiglaIdentico(sigla);

		if (paisIdentico != null && !paisIdentico.equals(getBean())) {
			throw new ValidatorException(new FacesMessage(severity,
					"Sigla informada já existe no sistema", null));
		}

	}
	
	
}