package br.edu.ifam.snaa.view;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import br.edu.ifam.snaa.business.ArraiaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.Arraia;
import br.edu.ifam.snaa.domain.notificacao.ImagemArraia;
import br.edu.ifam.snaa.util.JSFUtil;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@ViewController
@PreviousView("/pages/administrativo/cadastros/arraia/arraiaList.jsf")
@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class ArraiaBean extends AbstractEditPageBean<Arraia, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ArraiaBC arraiaBC;

	@Inject
	private JSFUtil jsfUtil;

	@Inject
	private ImagemArraia imagemArraia;

	@Override
	@Transactional
	public String delete() {
		this.arraiaBC.delete(getId());
		return getPreviousView();
	}

	@Override
	@Transactional
	public String insert() {
		getBean().setStatus(StatusRegistro.ATIVO);
		this.arraiaBC.insert(getBean());

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		setBean(createBean());
		return null;
	}

	@Override
	@Transactional
	public String update() {

		this.arraiaBC.update(getBean());
		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO,
				"operacao.salvar.sucesso");
		return null;
	}

	@Override
	protected Arraia handleLoad(Long id) {
		return this.arraiaBC.load(getId());

	}

	@Transactional
	public void efetuarUpload(FileUploadEvent event) {

		Arraia arraia = arraiaBC.load(getId());
		byte[] imagem = event.getFile().getContents();
		ImagemArraia imagemArraia = new ImagemArraia(imagem);

		arraia.adicionarImagem(imagemArraia);

		setBean(arraiaBC.update(arraia));

	}

	public ImagemArraia getImagemArraia() {
		return imagemArraia;
	}

	public void setImagemArraia(ImagemArraia imagemArraia) {
		this.imagemArraia = imagemArraia;
	}

	@Transactional
	public void excluirImagem() {
		this.getBean().removeImagem(imagemArraia);
	}

	public void validateNome(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;
		String nome = value.toString();

		Arraia arraiaIdentico = this.arraiaBC.buscarPorNomeIdentico(nome);

		if (arraiaIdentico != null && !arraiaIdentico.equals(getBean())) {
			throw new ValidatorException(new FacesMessage(severity,
					"Nome informado já existe no sistema", null));
		}

	}

	public void validateNomeCientifico(FacesContext context,
			UIComponent component, Object value) throws ValidatorException {
		Severity severity = FacesMessage.SEVERITY_ERROR;
		String nome = value.toString();

		Arraia arraiaIdentico = this.arraiaBC
				.buscarPorNomeCientificoIdentico(nome);

		if (arraiaIdentico != null && !arraiaIdentico.equals(getBean())) {
			throw new ValidatorException(new FacesMessage(severity,
					"Nome Cientifico informado já existe no sistema", null));
		}

	}

}