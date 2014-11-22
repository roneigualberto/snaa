package br.edu.ifam.snaa.domain.notificacao.naooficial;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifam.snaa.domain.SexoEnum;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.endereco.Pais;

@Entity
@Table(name = "vitima_nao_oficial")
public class VitimaNaoOficial implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(name = "data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Column(length = 11)
	private String cpf;
	
	@Column
	private Boolean estrangeiro=Boolean.FALSE;
	
	@Column(length=11)
	private String passaporte;

	@Column(length = 20)
	private String rg;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SexoEnum sexo;

	@ManyToOne
	@JoinColumn(name = "pais_id")
	private Pais paisOrigem;

	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipioOrigem;

	@OneToOne(mappedBy = "vitima")
	private AcidenteNaoOficial acidente;

	@Enumerated(EnumType.STRING)
	@Column(name = "status_registro", nullable = false)
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Pais getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(Pais paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public Municipio getMunicipioOrigem() {
		return municipioOrigem;
	}

	public void setMunicipioOrigem(Municipio municipioOrigem) {
		this.municipioOrigem = municipioOrigem;
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
		VitimaNaoOficial other = (VitimaNaoOficial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public StatusRegistro getStatus() {
		return status;
	}

	public void setStatus(StatusRegistro status) {
		this.status = status;
	}

	

	public Boolean getEstrangeiro() {
		return estrangeiro;
	}

	public void setEstrangeiro(Boolean estrangeiro) {
		this.estrangeiro = estrangeiro;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}
	
	public String getDocumento() {
		return getEstrangeiro() ? getPassaporte() + "(PASSAPORTE)" : getCpf() + "(CPF)";
	}
	
	

}
