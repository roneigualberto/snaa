package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.endereco.Endereco;
import br.edu.ifam.snaa.domain.endereco.Municipio;

@Entity
@Table(name = "unidade_saude")
public class UnidadeSaude implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 14, unique = true)
	private String codigo;

	@ManyToOne
	@Inject
	@JoinColumn(nullable = false)
	private Endereco endereco;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(name = "data_criacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;

	/*
	 * @ElementCollection
	 * 
	 * @CollectionTable(name = "unidade_saude_email")
	 * 
	 * @Column(name = "email") private Set<String> emails = new
	 * HashSet<String>();
	 */

	@ElementCollection
	@CollectionTable(name = "unidade_saude_telefone")
	@Column(name = "telefone")
	private Set<String> telefones = new HashSet<String>();

	@OneToOne(mappedBy = "unidadeSaude")
	@Inject
	private RepresentanteUnidadeSaude representante;

	@ManyToOne
	private Municipio municipio;

	@Column(name = "situacao_registro")
	@Enumerated(EnumType.STRING)
	private StatusRegistro status = StatusRegistro.ATIVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public RepresentanteUnidadeSaude getRepresentante() {
		return representante;
	}

	public void setRepresentante(RepresentanteUnidadeSaude representante) {
		this.representante = representante;
		representante.setUnidadeSaude(this);
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(StatusRegistro situacaoRegistro) {
		this.status = situacaoRegistro;
	}

	public void addTelefone(String telefone) {

		this.telefones.add(telefone); 

	}

	public void removeTelefone(String nome) {
		this.telefones.remove(nome);
	}

}
