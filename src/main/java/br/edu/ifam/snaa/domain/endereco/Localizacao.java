package br.edu.ifam.snaa.domain.endereco;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Localizacao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=255)
	private String descricao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Municipio municipio;
	
	@ManyToOne
	@JoinColumn(name="tipo_localizacao_id",nullable=false)
	private TipoLocalizacao tipoLocalizacao;
	
	public Localizacao() {
		super();
	}
	

	public Localizacao(String nome) {
		super();
		this.descricao = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public TipoLocalizacao getTipoLocalizacao() {
		return tipoLocalizacao;
	}

	public void setTipoLocalizacao(TipoLocalizacao tipoLocalizacao) {
		this.tipoLocalizacao = tipoLocalizacao;
	}
}
