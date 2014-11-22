package br.edu.ifam.snaa.domain.endereco;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.edu.ifam.snaa.domain.StatusRegistro;

@Entity
@Cacheable(value=true)
public class Pais implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 203326309465953689L;

	@Id  @GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id;
	
	@Column(unique=true,nullable=false,length=255)
	private String nome;
	
	@Column(unique=true,length=5)
	private	String sigla;
	
	@Column(length=255)
	private String nacionalidade;
	
	@Column(name="status_registro")
	@Enumerated(EnumType.STRING)
	private StatusRegistro status;
	
	@OneToMany(mappedBy="pais")
	private List<Estado> estados;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public boolean isAtivo() {
		return StatusRegistro.ATIVO.equals(this.status);
	}
	public StatusRegistro getStatus() {
		return status;
	}
	public void setStatus(StatusRegistro status) {
		this.status = status;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	
	
	

}
