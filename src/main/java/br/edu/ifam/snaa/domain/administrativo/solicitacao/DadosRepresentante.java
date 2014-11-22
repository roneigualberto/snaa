package br.edu.ifam.snaa.domain.administrativo.solicitacao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifam.snaa.domain.SexoEnum;
import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.edu.ifam.snaa.domain.seguranca.Usuario;

/**
 * @author Ronei
 *  Armazena os dados do representante de unidade informado 
 *  na solicitação de criação de conta
 */

@Embeddable
public class DadosRepresentante implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(nullable = false, length = 30)
	private String rg;

	@Column(length = 255, nullable = false)
	private String nome;

	@Column(name = "data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SexoEnum sexo;

	@Column(length = 30, nullable = false)
	private String senha;

	@Column(length = 255, nullable = false)
	private String email;

	@Column
	private String telefone;

	@OneToOne(fetch=FetchType.LAZY,mappedBy="solicitacao")
	private DocumentoAnexo documento;

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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public DocumentoAnexo getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoAnexo documento) {
		this.documento = documento;
	}
	
	public RepresentanteUnidadeSaude buildRepresentante() {
		RepresentanteUnidadeSaude representante = new RepresentanteUnidadeSaude();
		
		representante.setEmail(email);
		representante.setStatus(StatusRegistro.ATIVO);
		representante.setTelefone(telefone);
		representante.setUsuario(new Usuario());
		
		representante.getUsuario().setCpf(cpf);
		representante.getUsuario().setEmail(email);
		representante.getUsuario().setRg(rg);
		representante.getUsuario().setSenha(senha);
		representante.getUsuario().setSexo(sexo);
		representante.getUsuario().setStatus(StatusRegistro.ATIVO);
		representante.getUsuario().setNome(nome);
		representante.getUsuario().setDataNascimento(dataNascimento);
		representante.getUsuario().setTelefone(telefone);
		
		
		return representante;
	}

}
