package br.edu.ifam.snaa.domain.administrativo.solicitacao;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import br.edu.ifam.snaa.domain.administrativo.Administrador;


/**
 * @author Ronei
 * 
 * Entidade que representa a solitação de criação de conta
 * de representante da unidade de saúde
 *  
 */
@Entity
@Table(name = "solicitacao_conta")
public class SolicitacaoConta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Inject
	@Embedded
	private DadosUnidade unidade;

	@Inject
	@Embedded
	private DadosRepresentante representante;

	@Column(name = "data_envio",nullable=false)
	private Date dataEnvio;

	@ManyToOne
	@JoinColumn(name="avaliador")
	private Administrador avaliador;

	@Column(name = "data_avaliacao")
	private Date dataAvaliacao;

	@Column(name = "situacao_registro", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusRegistro status;
	
	@Column(name="mensagem")
	private String mensagem;

	public DadosUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(DadosUnidade unidade) {
		this.unidade = unidade;
	}

	public DadosRepresentante getRepresentante() {
		return representante;
	}

	public void setRepresentante(DadosRepresentante representante) {
		this.representante = representante;
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(StatusRegistro situacaoRegistro) {
		this.status = situacaoRegistro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Administrador getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Administrador avaliador) {
		this.avaliador = avaliador;
	}

	
	public void setDocumento(DocumentoAnexo documento) {
		this.representante.setDocumento(documento);
		documento.setSolicitacao(this);
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	
	
	public String getCodigo() {

		return String.format("%05d", this.id);
	}
	

}
