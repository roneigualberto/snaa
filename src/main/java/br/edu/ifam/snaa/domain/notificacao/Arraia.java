package br.edu.ifam.snaa.domain.notificacao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Arraia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4497361175544572081L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(name = "nome_cientifico", nullable = false, unique = true)
	private String nomeCientifico;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "arraia")
	private List<ImagemArraia> imagens;

	@Column(nullable = false, name = "status_registro")
	@Enumerated(EnumType.STRING)
	private StatusRegistro status;

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

	public String getNomeCientifico() {
		return nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	public List<ImagemArraia> getImagens() {
		return imagens;
	}

	public void adicionarImagem(ImagemArraia imagemArraia) {

		if (this.imagens.contains(imagemArraia)) {
			return;
		}
		imagemArraia.setArraia(this);
		this.imagens.add(imagemArraia);

	}
	
	public void removeImagem(ImagemArraia imagemArraia) {
		this.imagens.remove(imagemArraia);
	}
	
	public boolean isAtivo() {
		return StatusRegistro.ATIVO.equals(status);
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(StatusRegistro status) {
		this.status = status;
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
		Arraia other = (Arraia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
