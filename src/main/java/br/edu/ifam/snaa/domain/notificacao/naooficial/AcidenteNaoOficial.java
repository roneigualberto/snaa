package br.edu.ifam.snaa.domain.notificacao.naooficial;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.notificacao.AtividadeVitima;
import br.edu.ifam.snaa.domain.notificacao.DadoCircunstancial;
import br.edu.ifam.snaa.domain.notificacao.ImagemArraia;
import br.edu.ifam.snaa.domain.notificacao.LocalAnatomicoEnum;
import br.edu.ifam.snaa.domain.notificacao.PeriodoEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.SituacaoAnimal;
import br.edu.ifam.snaa.util.RespostaEnum;

@Entity
@Table(name = "acidente_nao_oficial")
public class AcidenteNaoOficial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ocorrencia", nullable = false, updatable = false)
	private Date dataOcorrencia;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	@Inject
	private VitimaNaoOficial vitima;

	@Embedded
	private DadoCircunstancial dadoCircunstancial;

	@Enumerated(EnumType.STRING)
	@Column(name = "local_anatomico",nullable=false)
	private LocalAnatomicoEnum localAnatomico;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Municipio municipio;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn
	private Localizacao localizacao;

	@Enumerated(EnumType.STRING)
	private PeriodoEnum periodo;

	@ManyToOne
	private ImagemArraia imagem;

	@Enumerated(EnumType.STRING)
	@Column(name = "arraia_foi_vista")
	private RespostaEnum arraiaFoiVista;

	@Column(name = "escala_certeza")
	private Integer escalaCerteza;

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_animal")
	private SituacaoAnimal situacaoAnimal;

	@ManyToOne
	@JoinColumn(name = "atividade_vitima")
	private AtividadeVitima atividadeVitima;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

	public ImagemArraia getImagem() {
		return imagem;
	}

	public void setImagem(ImagemArraia imagem) {
		this.imagem = imagem;
	}

	public DadoCircunstancial getDadoCircunstancial() {
		if (dadoCircunstancial == null) {
			dadoCircunstancial = new DadoCircunstancial();
		}
		return dadoCircunstancial;
	}

	public void setDadoCircunstancial(DadoCircunstancial dadoCircunstancial) {
		this.dadoCircunstancial = dadoCircunstancial;
	}

	public VitimaNaoOficial getVitima() {
		return vitima;
	}

	public void setVitima(VitimaNaoOficial vitima) {
		this.vitima = vitima;
	}

	public AtividadeVitima getAtividadeVitima() {
		return atividadeVitima;
	}

	public void setAtividadeVitima(AtividadeVitima atividadeVitima) {
		this.atividadeVitima = atividadeVitima;
	}

	public LocalAnatomicoEnum getLocalAnatomico() {
		return localAnatomico;
	}

	public void setLocalAnatomico(LocalAnatomicoEnum localAnatomico) {
		this.localAnatomico = localAnatomico;
	}

	public RespostaEnum getArraiaFoiVista() {
		return arraiaFoiVista;
	}

	public void setArraiaFoiVista(RespostaEnum arraiaFoiVista) {
		this.arraiaFoiVista = arraiaFoiVista;
	}

	public Integer getEscalaCerteza() {
		return escalaCerteza;
	}

	public void setEscalaCerteza(Integer escalaCerteza) {
		this.escalaCerteza = escalaCerteza;
	}

	public SituacaoAnimal getSituacaoAnimal() {
		return situacaoAnimal;
	}

	public void setSituacaoAnimal(SituacaoAnimal situacaoAnimal) {
		this.situacaoAnimal = situacaoAnimal;
	}

	public AcidenteNaoOficial() {
		
	}
}
