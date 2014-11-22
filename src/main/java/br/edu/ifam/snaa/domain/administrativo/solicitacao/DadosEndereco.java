package br.edu.ifam.snaa.domain.administrativo.solicitacao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.edu.ifam.snaa.domain.endereco.Bairro;
import br.edu.ifam.snaa.domain.endereco.Endereco;
import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.domain.endereco.Logradouro;


/**
 * 
 * 
 * @author Ronei Macedo Gualberto
 *
 * Armazena os dados do endereço da unidade de saúde
 * informado na solicitação de criação de conta de representante
 * de unidade
 */
@Embeddable
public class DadosEndereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@Inject
	private Logradouro logradouro;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@Inject
	private Bairro bairro;

	@Column
	private String numero;

	@Column
	private String complemento;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@Inject
	private Localizacao localizacao;

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
		
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
		this.logradouro.setBairro(bairro);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
	public Endereco buildEndereco() {
		Endereco endereco = new Endereco();
		
		
		endereco.setBairro(bairro);
		endereco.setLocalizacao(localizacao);
		endereco.setLogradouro(logradouro);
		endereco.setComplemento(complemento);
		endereco.setNumero(numero);
		
		return  endereco;
		
	}

}
