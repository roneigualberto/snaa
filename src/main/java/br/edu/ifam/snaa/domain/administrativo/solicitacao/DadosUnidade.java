package br.edu.ifam.snaa.domain.administrativo.solicitacao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifam.snaa.domain.endereco.Municipio;
import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;

@Embeddable
/**
 * @author Ronei
 *  Armazena os dados da unidade de saúde informado 
 *  na solicitação de criação de conta
 */
public class DadosUnidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false,length=14)
	private String codigo;

	@Column(name = "nome_unidade", nullable = false,length=255)
	private String nome;

	@Embedded
	@Inject
	private DadosEndereco endereco;

	@ManyToOne
	private Municipio municipio;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "telefone_solicitacao_conta")
	@Column(name = "telefone")
	private Set<String> telefones = new HashSet<String>();

	@Column(name = "data_criacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	



	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public DadosEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(DadosEndereco endereco) {
		this.endereco = endereco;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public void addTelefone(String telefone) {

		this.telefones.add(telefone);

	}

	public void removeTelefone(String nome) {
		this.telefones.remove(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public UnidadeSaude buildUnidadeSaude() {
		UnidadeSaude unidadeSaude = new UnidadeSaude();
		
		unidadeSaude.setCodigo(getCodigo());
		unidadeSaude.setNome(getNome());
		unidadeSaude.setDataCriacao(getDataCriacao());
		unidadeSaude.setMunicipio(getMunicipio());
		unidadeSaude.setTelefones(getTelefones());
		unidadeSaude.setEndereco(endereco.buildEndereco());
		
		
		return unidadeSaude;
	}

}
