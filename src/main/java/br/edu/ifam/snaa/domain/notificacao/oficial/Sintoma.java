package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sintoma implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String descricao;
	
	
	@Enumerated(EnumType.STRING)
	private TipoSintomaEnum tipo;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public TipoSintomaEnum getTipo() {
		return tipo;
	}


	public void setTipo(TipoSintomaEnum tipo) {
		this.tipo = tipo;
	}
	
	
	
	public Sintoma() {
	}
	
	public Sintoma(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	

}
