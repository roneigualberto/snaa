package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
import br.edu.ifam.snaa.domain.notificacao.SituacaoAcidenteEnum;
import br.edu.ifam.snaa.util.RespostaEnum;

@Entity
@Table(name = "acidente")
public class Acidente implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int NUM_READYONLY_ACIDENTE = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ocorrencia", nullable = false, updatable = false)
	private Date dataOcorrencia;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Vitima vitima;

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ultimo_atendimento", unique = true)
	private Atendimento ultimoAtendimento;

	@OneToMany(mappedBy = "acidente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profissional_saude_id",nullable=false)
	private ProfissionalSaude profissionalSaude;
	
	
	@ManyToOne
	private ImagemArraia imagem;

	@Enumerated(EnumType.STRING)
	@Column(name = "arraia_foi_vista")
	private RespostaEnum arraiaFoiVista;

	@Enumerated(EnumType.STRING)
	@Column(name = "escala_certeza")
	private Integer escalaCerteza;

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao_animal")
	private SituacaoAnimal situacaoAnimal;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "atividade_vitima")
	private AtividadeVitima atividadeVitima;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "situacao")
	private SituacaoAcidenteEnum situacao;

	@ManyToOne
	@JoinColumn(name = "quadro_saida_id")
	private QuadroSaida quadroSaida;

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

	public Atendimento getUltimoAtendimento() {
		return ultimoAtendimento;
	}

	public void gerarAtendimento(ProfissionalSaude profissionalSaude) {

		Atendimento atendimento = new Atendimento();

		if (this.atendimentos.contains(atendimento)) {
			return;
		}

		atendimento.setProfissionalSaude(profissionalSaude);

		this.atendimentos.add(atendimento);
		this.ultimoAtendimento = atendimento;
		ultimoAtendimento.setAcidente(this);
	}

	public void adicionarAtendimento(Atendimento atendimento) {

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

	public Vitima getVitima() {
		return vitima;
	}

	public void setVitima(Vitima vitima) {
		this.vitima = vitima;
	}

	public ProfissionalSaude getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}

	public AtividadeVitima getAtividadeVitima() {
		return atividadeVitima;
	}

	public void setAtividadeVitima(AtividadeVitima atividadeVitima) {
		this.atividadeVitima = atividadeVitima;
	}

	public void setUltimoAtendimento(Atendimento ultimoAtendimento) {
		this.ultimoAtendimento = ultimoAtendimento;
	}

	public LocalAnatomicoEnum getLocalAnatomico() {
		return localAnatomico;
	}

	public void setLocalAnatomico(LocalAnatomicoEnum localAnatomico) {
		this.localAnatomico = localAnatomico;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public SituacaoAcidenteEnum getSituacao() {
		return situacao;
	}

	public boolean isFinalizado() {
		return SituacaoAcidenteEnum.CONCLUIDO.equals(situacao);

	}

	public QuadroSaida getQuadroSaida() {
		return quadroSaida;
	}

	public void setQuadroSaida(QuadroSaida quadroSaida) {
		this.quadroSaida = quadroSaida;
	}

	public boolean isReadOnlyVitima() {

		return this.atendimentos.size() > NUM_READYONLY_ACIDENTE;
	}

	public Acidente() {

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

	public void reativar(ProfissionalSaude profissional) {

		this.situacao = SituacaoAcidenteEnum.PENDENTE;
		this.gerarAtendimento(profissional);
	}

	

	public void concluir() {
		this.situacao = SituacaoAcidenteEnum.CONCLUIDO;

	}

	public String getCodigo() {

		return String.format("%05d", this.id);
	}

	
	

}
