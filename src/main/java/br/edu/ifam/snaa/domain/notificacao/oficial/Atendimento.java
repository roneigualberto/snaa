package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atendimento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2655317397243386526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_registro")
	private Calendar dataRegistro = Calendar.getInstance();

	@ManyToOne
	@JoinColumn(name = "profissional_saude_id",nullable=false)
	private ProfissionalSaude profissionalSaude;

	@ManyToOne
	private Acidente acidente;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "atendimento")
	private Prontuario prontuario;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "atendimento")
	private Tratamento tratamento;
	
	@ManyToOne
	@JoinColumn(name = "quadro_saida_id")
	private QuadroSaida quadroSaida;
	
	@Column
	private String comentario;
	

	public Atendimento() {
	}

	public Atendimento(Acidente acidente, ProfissionalSaude profissionalSaude) {

		this.profissionalSaude = profissionalSaude;
		this.acidente.adicionarAtendimento(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProfissionalSaude getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}

	public Acidente getAcidente() {
		return acidente;
	}

	public void setAcidente(Acidente acidente) {
		this.acidente = acidente;

	}

	public Prontuario getProntuario() {
		return prontuario;
	}

	public void setProntuario(Prontuario prontuario) {

		this.prontuario = prontuario;

	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public QuadroSaida getQuadroSaida() {
		return quadroSaida;
	}

	public void setQuadroSaida(QuadroSaida quadroSaida) {
		this.quadroSaida = quadroSaida;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public String getCodigo() {

		return String.format("%05d", this.id);
	}
	
	
	

}
