package br.edu.ifam.snaa.view;

import static br.edu.ifam.snaa.constant.Constantes.PERFIL_PROFISSIONAL_SAUDE;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import br.edu.ifam.snaa.business.AcidenteBC;
import br.edu.ifam.snaa.business.AtividadeVitimaBC;
import br.edu.ifam.snaa.business.EscolaridadeBC;
import br.edu.ifam.snaa.business.EstadoBC;
import br.edu.ifam.snaa.business.ImagemArraiaBC;
import br.edu.ifam.snaa.business.LocalizacaoBC;
import br.edu.ifam.snaa.business.MunicipioBC;
import br.edu.ifam.snaa.business.ProfissaoBC;
import br.edu.ifam.snaa.business.TipoLocalizacaoBC;
import br.edu.ifam.snaa.business.VitimaBC;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.domain.endereco.TipoLocalizacao;
import br.edu.ifam.snaa.domain.notificacao.DadoCircunstancial;
import br.edu.ifam.snaa.domain.notificacao.ImagemArraia;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.edu.ifam.snaa.security.UsuarioSessao;
import br.edu.ifam.snaa.util.JSFUtil;
import br.edu.ifam.snaa.util.RespostaEnum;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Parameter;

@ViewController
@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
public class AcidenteBean extends AbstractEditPageBean<Acidente, Long> {

	private static final long serialVersionUID = 1L;

	/* Declaração dos Business Controller */
	@Inject
	private AcidenteBC acidenteBC;
	@Inject
	private VitimaBC vitimaBC;
	@Inject
	private MunicipioBC municipioBC;
	@Inject
	private EstadoBC estadoBC;
	@Inject
	private LocalizacaoBC localizacaoBC;
	@Inject
	private TipoLocalizacaoBC tipoLocalizacaoBC;
	@Inject
	private ProfissaoBC profissaoBC;
	@Inject
	private EscolaridadeBC escolaridadeBC;
	@Inject
	private AtividadeVitimaBC atividadeVitimaBC;
	@Inject
	private ImagemArraiaBC imagemArraiaBC;
	/* FIM */

	@Inject
	private TipoLocalizacao tipoLocalizacao;
	@Inject
	private JSFUtil jsfUtil;
	@Inject
	private UsuarioSessao usuarioSessao;
	@Inject
	private SnaaContext snaaContext;
	private Estado estado;
	private List<Estado> estados;
	private List<Municipio> municipios;

	@Inject
	private Parameter<Long> idVitima;
	private Long idImagemArraia;

	@PostConstruct
	@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
	public void carregarDadosDoAcidente() {
		carregarVitima();
		carregarPais();
		carregarEstado();
		carregarLocalizacao();
	}

	private void carregarVitima() {

		if (isUpdateMode()) {
			return;
		}

		Vitima vitima = new Vitima();
		boolean vitimaExistente = idVitima.getValue() != null;

		if (vitimaExistente) {
			vitima = vitimaBC.load(idVitima.getValue());
		}
		getBean().setVitima(vitima);
	}

	private void carregarEstado() {

		if (getBean().getVitima().getId() != null &&  getBean().getVitima().getMunicipioOrigem() != null) {
			this.estado = getBean().getVitima().getMunicipioOrigem()
					.getEstado();
		} else {
			estado = snaaContext.getEstado();
		}

	}

	private void carregarPais() {
		if (getBean().getVitima().getId() == null) {
			getBean().getVitima().setPaisOrigem(snaaContext.getPais());
		}
	}

	/**
	 * 
	 * Carrega a localização quando o usuário for editar o acidente
	 * 
	 */
	private void carregarLocalizacao() {
		if (getBean().getLocalizacao() != null) {
			Long idTipoLocalizacao = getBean().getLocalizacao()
					.getTipoLocalizacao().getId();
			this.tipoLocalizacao = this.tipoLocalizacaoBC
					.load(idTipoLocalizacao);
		}
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	@Transactional
	public String insert() {

		Acidente acidente = getBean();
		carregarDados(acidente);
		acidenteBC.insert(acidente);

		return "/pages/profissionalsaude/acidente/acidenteForm?faces-redirect=true&id="
				+ acidente.getId();
	}

	private void carregarDados(Acidente acidente) {
		acidente.setProfissionalSaude(usuarioSessao.getProfissionalSaude());
		setarVitima(acidente);
		setarProfissao(acidente);
		setarAtividade(acidente);
		setarEscolaridade(acidente);
		setarLocalizacao(acidente);
	}

	private void setarLocalizacao(Acidente acidente) {
		if (acidente.getLocalizacao() != null) {
			Long idLocalizacao = acidente.getLocalizacao().getId();
			if (idLocalizacao != null) {
				acidente.setLocalizacao(localizacaoBC.load(idLocalizacao));
			} else {
				acidente.getLocalizacao().setMunicipio(acidente.getMunicipio());
				acidente.getLocalizacao().setTipoLocalizacao(tipoLocalizacao);
			}

		}
	}

	private void setarAtividade(Acidente acidente) {
		if (acidente.getAtividadeVitima() != null) {
			Long idAtividadeVitima = acidente.getAtividadeVitima().getId();
			if (idAtividadeVitima != null) {
				acidente.setAtividadeVitima(this.atividadeVitimaBC
						.load(idAtividadeVitima));
			}
		}
	}

	private void setarProfissao(Acidente acidente) {
		DadoCircunstancial dadoCircunstancial = acidente
				.getDadoCircunstancial();
		if (dadoCircunstancial.getProfissao() != null) {
			Long idProfissao = dadoCircunstancial.getProfissao().getId();
			if (idProfissao != null) {
				dadoCircunstancial.setProfissao(profissaoBC.load(idProfissao));
			}
		}

	}

	private void setarVitima(Acidente acidente) {
		acidente.setProfissionalSaude(usuarioSessao.getProfissionalSaude());

		acidente.getVitima().setProfissionalSaude(
				usuarioSessao.getProfissionalSaude());

		if (Util.isNotNull(acidente.getVitima().getId())) {
			Vitima newVitima = vitimaBC.update(acidente.getVitima());

			acidente.setVitima(newVitima);
		}
	}

	private void setarEscolaridade(Acidente acidente) {

		DadoCircunstancial dadoCircunstancial = acidente
				.getDadoCircunstancial();
		if (dadoCircunstancial.getEscolaridade() != null) {
			Long idEscolaridade = dadoCircunstancial.getEscolaridade().getId();
			if (idEscolaridade != null) {
				dadoCircunstancial.setEscolaridade(escolaridadeBC
						.load(idEscolaridade));
			}
		}
	}

	@Override
	protected Acidente handleLoad(Long id) {
		return this.acidenteBC.load(getId());
	}

	public TipoLocalizacao getTipoLocalizacao() {
		return tipoLocalizacao;
	}

	public void setTipoLocalizacao(TipoLocalizacao tipoLocalizacao) {
		this.tipoLocalizacao = tipoLocalizacao;
	}

	/**
	 * 
	 * @param nome
	 * @return
	 */

	public List<Localizacao> handleAutoCompleteLocalizacao(String nome) {
		Localizacao localizacao = new Localizacao(nome);
		localizacao.setMunicipio(getBean().getMunicipio());
		localizacao.setTipoLocalizacao(tipoLocalizacao);

		return localizacaoBC.buscar(localizacao);
	}

	public void handleChangeMunicipio(AjaxBehaviorEvent event) {
		if (getBean().getLocalizacao() != null
				&& getBean().getLocalizacao().getId() != null) {
			getBean().setLocalizacao(null);
		}

	}

	public void handleChangeTipoLocalizacao(AjaxBehaviorEvent event) {

		if (getBean().getLocalizacao() != null
				&& getBean().getLocalizacao().getId() != null) {
			getBean().setLocalizacao(null);
		}

	}

	public void handleSelectLocalizacao(SelectEvent event) {
		Localizacao localizacao = (Localizacao) event.getObject();

		Municipio municipio = localizacao.getMunicipio();

		getBean().setMunicipio(municipio);
		this.tipoLocalizacao = localizacao.getTipoLocalizacao();

	}

	@Transactional
	@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
	public void updateVitima() {
		String key = "vitima.update.sucesso";
		Vitima vitima = getBean().getVitima();

		Acidente acidente = acidenteBC.load(getBean().getId());

		acidente.getDadoCircunstancial().setEscolaridade(
				getBean().getDadoCircunstancial().getEscolaridade());
		acidente.getDadoCircunstancial().setProfissao(
				getBean().getDadoCircunstancial().getProfissao());

		vitimaBC.update(vitima);
		acidenteBC.update(acidente);

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO, key);

	}

	@Transactional
	@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
	public void updateArraia() {
		String key = "imagemarraia.save.sucesso";
		Acidente acidente = this.acidenteBC.load(getBean().getId());

		if (idImagemArraia != null) {
			acidente.setImagem(imagemArraiaBC.load(idImagemArraia));
		}

		if (RespostaEnum.N.equals(getBean().getArraiaFoiVista())
				|| getBean().getArraiaFoiVista() == null) {
			acidente.setImagem(null);
			idImagemArraia = null;
		}

		acidente.setArraiaFoiVista(getBean().getArraiaFoiVista());
		acidente.setEscalaCerteza(getBean().getEscalaCerteza());
		acidente.setSituacaoAnimal(getBean().getSituacaoAnimal());

		acidenteBC.update(acidente);

		jsfUtil.addMessage(FacesMessage.SEVERITY_INFO, key);

	}

	@Transactional
	@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
	public String concluir() {
		Acidente acidente = acidenteBC.load(getBean().getId());

		if (!validarConclusao(acidente)) {
			return null;
		}

		acidenteBC.concluir(getBean());

		return "/pages/profissionalsaude/acidente/acidenteForm?faces-redirect=true&id="
				+ acidente.getId();

	}

	@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
	public String reativar() {

		this.acidenteBC.reativar(getBean(),
				usuarioSessao.getProfissionalSaude());

		return "/pages/profissionalsaude/acidente/acidenteForm?faces-redirect=true&id="
				+ getBean().getId();
	}

	private boolean validarConclusao(Acidente acidente) {
		boolean result = true;
		boolean prontuarioNaoCadastrado = acidente.getUltimoAtendimento()
				.getProntuario() == null;
		boolean tratamentoNaoCadastrado = acidente.getUltimoAtendimento()
				.getTratamento() == null;
		if (prontuarioNaoCadastrado) {
			jsfUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"conclusao.prontuario");
			result = false;
		}
		if (tratamentoNaoCadastrado) {
			jsfUtil.addMessage(FacesMessage.SEVERITY_ERROR,
					"conclusao.tratamento");
			result = false;
		}
		return result;
	}

	

	public void changeImagemArraia() {
		getBean().getImagem();
	}
	
	
	public void changePais() {
		
		if (!getBean().getVitima().isEstrangeiro()) {
			getBean().getVitima().setPaisOrigem(null);
		}
		else {
			getBean().getVitima().setPaisOrigem(snaaContext.getPais());
		}
	}

	public Long getIdImagemArraia() {
		if (idImagemArraia == null && getBean().getImagem() != null) {
			idImagemArraia = getBean().getImagem().getId();
		}
		return idImagemArraia;
	}

	public void setIdImagemArraia(Long idImagemArraia) {
		this.idImagemArraia = idImagemArraia;
	}

	public List<ImagemArraia> getListImagemArraia() {

		return imagemArraiaBC.findAll();
	}

	public void setListImagemArraia(List<ImagemArraia> imagemArraia) {
	}

	public boolean getEstrangeiro() {

		Pais paisOrigem = getBean().getVitima().getPaisOrigem();

		boolean estrangeiro = !snaaContext.getPais().equals(paisOrigem);

		return estrangeiro || getBean().getVitima().isEstrangeiro();

	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		if (estados == null) {
			estados = this.estadoBC.buscarPorPais(getBean().getVitima()
					.getPaisOrigem());
		}
		return estados;
	}

	public List<Municipio> getMunicipios() {
		municipios = this.municipioBC.buscarPorEstado(estado);
		return municipios;
	}

	@Override
	@Deprecated
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

}