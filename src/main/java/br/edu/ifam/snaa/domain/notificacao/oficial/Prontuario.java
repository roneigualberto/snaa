package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prontuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_registro")
	private Calendar dataRegistro = Calendar.getInstance();
		
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="prontuario_sintoma",
	joinColumns=@JoinColumn(name="prontuario_id"),
	inverseJoinColumns=@JoinColumn(name="sintoma_id"))
	private List<Sintoma> sintomas = new ArrayList<Sintoma>();
	
	
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="prontuario_doenca",
	joinColumns=@JoinColumn(name="prontuario_id"),
	inverseJoinColumns=@JoinColumn(name="doenca_id"))
	private List<Doenca> doencas = new ArrayList<Doenca>();
	
	@OneToOne
	private Atendimento atendimento;

	public List<Sintoma> getSintomas() {
		return this.sintomas;
	}

	public List<Doenca> getDoencas() {
		return this.doencas;
	}
	
	public void adicionarDoenca(Doenca doenca) {
		if (this.doencas.contains(doenca)) {
			return;
		}
		this.doencas.add(doenca);
	}
	
	
	public void adicionarDoenca(String nmDoenca) {
		
		adicionarDoenca(new Doenca(nmDoenca));
	}
	
	public void removerDoenca(Doenca doenca) {
		this.doencas.remove(doenca);
	}
	
	public void adicionarSintomaLocal(Sintoma sintoma) {
		if (this.sintomas.contains(sintoma)) {
			return;
		}
		sintoma.setTipo(TipoSintomaEnum.LOCAL);
		this.sintomas.add(sintoma);
		
	}
	
	
	public void adicionarSintomaSistemico(Sintoma sintoma) {
		if (this.sintomas.contains(sintoma)) {
			return;
		}
		sintoma.setTipo(TipoSintomaEnum.SISTEMICO);
		this.sintomas.add(sintoma);
		
	}
	
	public void removerSintomaSistemico(Sintoma sintoma) {
		
		this.sintomas.remove(sintoma);
	}
		
	public void removerDoenca(Sintoma sintoma) {
		this.sintomas.remove(sintoma);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public void setDoencas(List<Doenca> doencas) {
		this.doencas = doencas;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
		
		atendimento.setProntuario(this);
	}
	
	
	public List<Sintoma> getSintomasSistemico() {
		List<Sintoma> sintomasSistemico = new ArrayList<Sintoma>();
		
		for(Sintoma sintoma : this.sintomas) {
			if (TipoSintomaEnum.SISTEMICO.equals(sintoma.getTipo())) {
				sintomasSistemico.add(sintoma);
			}
		}
		
		return sintomasSistemico;
		
	}
	
	public List<Sintoma> getSintomasLocal() {
		List<Sintoma> sintomasLocal = new ArrayList<Sintoma>();
		
		for(Sintoma sintoma : this.sintomas) {
			if (TipoSintomaEnum.LOCAL.equals(sintoma.getTipo())) {
				sintomasLocal.add(sintoma);
			}
		}
		
		return sintomasLocal;
		
	}
	public Prontuario() {
		
	}

	public Prontuario(Atendimento atendimento) {
		
		this.atendimento = atendimento;
	}

	public void setSintomas(List<Sintoma> s) {
		// TODO Auto-generated method stub
		this.sintomas = s;
	}
	
	
	
	

}
