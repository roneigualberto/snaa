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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.seguranca.Usuario;

@Entity
@Table(name = "representante_unidade_saude")
public class RepresentanteUnidadeSaude implements Serializable {

	private static final long serialVersionUID = -724128508371360163L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	@Inject
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "unidade_saude_id")
	private UnidadeSaude unidadeSaude;

	@Column
	private String email;

	@Column
	private String telefone;

	@Column(name = "situacao_registro", nullable = false)
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

	public UnidadeSaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(StatusRegistro situacaoRegistro) {
		this.status = situacaoRegistro;
	}

}
