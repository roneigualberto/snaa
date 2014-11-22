package br.edu.ifam.snaa.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import br.edu.ifam.snaa.business.AcidenteNaoOficialBC;
import br.edu.ifam.snaa.business.EstadoBC;
import br.edu.ifam.snaa.business.ImagemArraiaBC;
import br.edu.ifam.snaa.business.LocalizacaoBC;
import br.edu.ifam.snaa.business.MunicipioBC;
import br.edu.ifam.snaa.business.ProfissaoBC;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.endereco.Estado;
import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.endereco.Pais;
import br.edu.ifam.snaa.domain.endereco.TipoLocalizacao;
import br.edu.ifam.snaa.domain.notificacao.DadoCircunstancial;
import br.edu.ifam.snaa.domain.notificacao.ImagemArraia;
import br.edu.ifam.snaa.domain.notificacao.naooficial.AcidenteNaoOficial;
import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.edu.ifam.snaa.util.JSFUtil;
import br.edu.ifam.snaa.util.RespostaEnum;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
public class AcidenteNaoOficialBean extends
		AbstractEditPageBean<AcidenteNaoOficial, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcidenteNaoOficialBC acidenteBC;

	@Inject
	private MunicipioBC municipioBC;

	@Inject
	private EstadoBC estadoBC;

	@Inject
	private LocalizacaoBC localizacaoBC;

	@Inject
	private ProfissaoBC profissaoBC;

	@Inject
	private Vitima vitima;

	@Inject
	private DadoCircunstancial dadoCircunstancial;

	@Inject
	private TipoLocalizacao tipoLocalizacao;

	private Long idImagemArraia;

	@Inject
	private ImagemArraiaBC imagemArraiaBC;

	@Inject
	private JSFUtil jsfUtil;

	@Inject
	private SnaaContext snaaContext;

	private Estado estado;

	private List<Estado> estados;

	private List<Municipio> municipios;
	
	private String codVerificacao;
	
	private List<ImagemArraia> imagens;

	@PostConstruct
	public void init() {
		getBean().getVitima().setPaisOrigem(snaaContext.getPais());
		estado = snaaContext.getEstado();
		
		this.imagens = this.imagemArraiaBC.findAll();
	}

	@Override
	public String delete() {

		return null;
	}

	@Override
	@Transactional
	public String insert() {

		AcidenteNaoOficial acidente = getBean();
		acidente.getVitima().setStatus(StatusRegistro.ATIVO);
		loadDadosAcidente();
		acidenteBC.insert(acidente);

		setBean(Beans.getReference(AcidenteNaoOficial.class));
		tipoLocalizacao = new TipoLocalizacao();
		jsfUtil.addMessage(SeverityType.INFO, "notificacao.naooficial.sucesso");
		return null;
	}

	private void loadDadosAcidente() {

		AcidenteNaoOficial acidente = getBean();

		DadoCircunstancial dadoCircunstancial = acidente
				.getDadoCircunstancial();
		if (dadoCircunstancial.getProfissao() != null) {
			Long idProfissao = dadoCircunstancial.getProfissao().getId();
			if (idProfissao != null) {
				dadoCircunstancial.setProfissao(profissaoBC.load(idProfissao));
			}
		}

		if (acidente.getLocalizacao() != null) {
			Long idLocalizacao = acidente.getLocalizacao().getId();

			if (idLocalizacao != null) {
				acidente.setLocalizacao(localizacaoBC.load(idLocalizacao));
			} else {
				acidente.getLocalizacao().setMunicipio(acidente.getMunicipio());
				acidente.getLocalizacao().setTipoLocalizacao(tipoLocalizacao);
			}

		}

		if (idImagemArraia != null) {
			acidente.setImagem(imagemArraiaBC.load(idImagemArraia));
		}

		if (RespostaEnum.N.equals(getBean().getArraiaFoiVista())
				|| getBean().getArraiaFoiVista() == null) {
			acidente.setImagem(null);
			idImagemArraia = null;
		}

	}

	@Override
	protected AcidenteNaoOficial handleLoad(Long id) {
		return this.acidenteBC.load(id);
	}

	public TipoLocalizacao getTipoLocalizacao() {
		return tipoLocalizacao;
	}

	public void setTipoLocalizacao(TipoLocalizacao tipoLocalizacao) {
		this.tipoLocalizacao = tipoLocalizacao;
	}

	public Vitima getVitima() {
		return vitima;
	}

	public DadoCircunstancial getDadoCircunstancial() {
		return dadoCircunstancial;
	}

	public void setDadoCircunstancial(DadoCircunstancial dadoCircunstancial) {
		this.dadoCircunstancial = dadoCircunstancial;
	}

	public List<Localizacao> handleAutoCompleteLocalizacao(String nome) {
		Localizacao localizacao = new Localizacao(nome);
		localizacao.setMunicipio(getBean().getMunicipio());
		localizacao.setTipoLocalizacao(tipoLocalizacao);

		return localizacaoBC.buscar(localizacao);
	}

	public List<Municipio> getMunicipios() {

		municipios = this.municipioBC.buscarPorEstado(estado);

		return municipios;
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

	public void processValueChange(ValueChangeEvent event) {
	}

	public void changeImagemArraia() {
		getBean().getImagem();
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

		return imagens;
	}

	public void setListImagemArraia(List<ImagemArraia> imagemArraia) {
		System.out.println("");
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getEstrangeiro() {

		Pais paisOrigem = getBean().getVitima().getPaisOrigem();

		boolean estrangeiro = !snaaContext.getPais().equals(paisOrigem);

		return estrangeiro || getBean().getVitima().getEstrangeiro();

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

	public void changePais() {

		if (!getBean().getVitima().getEstrangeiro()) {
			getBean().getVitima().setPaisOrigem(null);
		} else {
			getBean().getVitima().setPaisOrigem(snaaContext.getPais());
		}
	}
	
	

}