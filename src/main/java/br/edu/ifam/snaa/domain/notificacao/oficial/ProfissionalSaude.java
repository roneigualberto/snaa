package br.edu.ifam.snaa.domain.notificacao.oficial;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.seguranca.Usuario;

@Entity
@Table(name = "profissional_saude")
public class ProfissionalSaude implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "usuario_id", nullable = false, unique = true)
	@Inject
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "unidade_saude_id",nullable=false)
	private UnidadeSaude unidadeSaude;

	@Column(nullable = false)
	private String crm;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusRegistro status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public ProfissionalSaude(Long id) {
		this.id = id;
	}

	public ProfissionalSaude() {
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
		usuario.setStatus(status);
	}
	
	
	public boolean isAtivo() {
		
		return StatusRegistro.ATIVO.equals(status);
	}

}
