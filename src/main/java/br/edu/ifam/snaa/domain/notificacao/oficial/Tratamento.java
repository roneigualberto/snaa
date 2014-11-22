package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tratamento implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private InternadoEnum internado  = InternadoEnum.N;
	
	@Column
	private Integer dias;
	
	@Column(name="medicamento_sistemico")
	private String medicamentoSistemico;
	
	@Column(name="categoria_sistemico")
	@Enumerated(EnumType.STRING)
	private CategoriaMedicamento categoriaSistemico = CategoriaMedicamento.NENHUM;
	
	@Column(name="medicamento_local")
	private String medicamentoLocal;
	
	@Column(name="categoria_local")
	@Enumerated(EnumType.STRING)
	private CategoriaMedicamento categoriaLocal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_registro")
	private Calendar dataRegistro = Calendar.getInstance();
	
	@OneToOne
	private Atendimento atendimento;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true,mappedBy="tratamento")
	private List<Exame> exames = new ArrayList<Exame>();

	public Tratamento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	public Tratamento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InternadoEnum getInternado() {
		return internado;
	}

	public void setInternado(InternadoEnum internado) {
		this.internado = internado;
		
		if (!isFicouInternado()) {
			this.dias = null;
		}
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getMedicamentoSistemico() {
		return medicamentoSistemico;
	}

	public void setMedicamentoSistemico(String medicamentoSistemico) {
		this.medicamentoSistemico = medicamentoSistemico;
	}

	public CategoriaMedicamento getCategoriaSistemico() {
		return categoriaSistemico;
	}

	public void setCategoriaSistemico(CategoriaMedicamento categoriaSistemico) {
		this.categoriaSistemico = categoriaSistemico;
	}

	public String getMedicamentoLocal() {
		return medicamentoLocal;
	}

	public void setMedicamentoLocal(String medicamentoLocal) {
		this.medicamentoLocal = medicamentoLocal;
	}

	public CategoriaMedicamento getCategoriaLocal() {
		return categoriaLocal;
	}

	public void setCategoriaLocal(CategoriaMedicamento categoriaLocal) {
		this.categoriaLocal = categoriaLocal;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	
	
	
	public Calendar getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Calendar dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public void adicionarExame(Exame exame) {
		if (this.exames.contains(exame)) {
			return;
		}
		this.exames.add(exame);
		exame.setTratamento(this);
		
	}
	
	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public void removerExame(Exame exame) {
		this.exames.remove(exame);
	
	}
	
	public boolean isFicouInternado() {
		
		return InternadoEnum.S.equals(internado);
	}
	
	
	
	
	
}
