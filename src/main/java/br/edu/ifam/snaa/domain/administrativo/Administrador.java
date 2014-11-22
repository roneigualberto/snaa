package br.edu.ifam.snaa.domain.administrativo;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.seguranca.Usuario;

@Entity
/**
 * 
 * 
 * @author Ronei
 *
 * Entidade que representa o administrador do sistema
 */
public class Administrador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 74712280296402129L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@Inject
	private Usuario usuario;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusRegistro status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(StatusRegistro status) {
		this.status = status;
		
	}

	public boolean isAtivo() {

		return StatusRegistro.ATIVO.equals(status);
	}
}
