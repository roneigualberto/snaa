package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Exame implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoExameEnum tipoExame;
	
	@Enumerated(EnumType.STRING)
	private EstadoExameEnum estadoExame;
	
	@Enumerated(EnumType.STRING)
    private NivelAlteracaoEnum nivelAlteracao;
	
	@ManyToOne
	private Tratamento tratamento;
	
	@Column
	private int valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoExameEnum getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExameEnum tipoExame) {
		this.tipoExame = tipoExame;
	}

	public EstadoExameEnum getEstadoExame() {
		return estadoExame;
	}

	public void setEstadoExame(EstadoExameEnum estadoExame) {
		
		this.estadoExame = estadoExame;
		
		if (!isAlterado()) {
			this.nivelAlteracao = null;
			this.valor = 0;
		}
	}

	public NivelAlteracaoEnum getNivelAlteracao() {
		return nivelAlteracao;
	}

	public void setNivelAlteracao(NivelAlteracaoEnum nivelAlteracao) {
		this.nivelAlteracao = nivelAlteracao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((tipoExame == null) ? 0 : tipoExame.hashCode());
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
		Exame other = (Exame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipoExame != other.tipoExame)
			return false;
		return true;
	}

	
    public boolean isAlterado() {
    	
    	return EstadoExameEnum.ALTERADO.equals(getEstadoExame());
    }
	
	

	
	
		
	
	
	
	
	
	
	

}
