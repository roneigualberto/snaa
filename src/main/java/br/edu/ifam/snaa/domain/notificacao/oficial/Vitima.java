

package br.edu.ifam.snaa.domain.notificacao.oficial;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.caelum.stella.format.CPFFormatter;
import br.edu.ifam.snaa.domain.SexoEnum;
import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.endereco.Pais;

@Entity
public class Vitima implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=255)
	private String nome;
	
	@Column(name="data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	
	@Column(unique=true,length=11)
	private String cpf;
	
	@Column
	private Boolean estrangeiro=Boolean.FALSE;
	
	@Column(unique=true,length=11)
	private String passaporte;
	
	@Column(length=20)
	private String rg;
	
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	@ManyToOne
	@JoinColumn(name="pais_id")
	private Pais paisOrigem;
	
	@ManyToOne
	@JoinColumn(name="municipio_id")
	private Municipio municipioOrigem;
	
	@OneToMany(mappedBy="vitima")
	private List<Acidente> acidentes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(updatable=true,name="profissional_saude_id")
	private ProfissionalSaude profissionalSaude;
	
	@Transient
	private Acidente ultimoAcidente;
	
	
	
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

	public List<Acidente> getAcidentes() {
		return acidentes;
	}

	public void setAcidentes(List<Acidente> acidentes) {
		this.acidentes = acidentes;
	}

	public Acidente getUltimoAcidente() {
		int index = this.acidentes.size() - 1;
				
		return index >= 0 ?  this.acidentes.get(index) : null;
	}

	public ProfissionalSaude getProfissionalSaude() {
		return profissionalSaude;
	}

	public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
		this.profissionalSaude = profissionalSaude;
	}
	

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public boolean isEstrangeiro() {
		return Boolean.TRUE.equals(estrangeiro);
	}

	public void setEstrangeiro(boolean estrangeiro) {
		this.estrangeiro = estrangeiro;
	}
	
	
	public String getDocumento() {
		return isEstrangeiro() ? getPassaporte() + " (PASSAPORTE)" : getCpfFormatado() + " (CPF)";
	}
	
	public String getCpfFormatado() {
		return new CPFFormatter().format(cpf);
		
	}
	
	
	
   
	

}
