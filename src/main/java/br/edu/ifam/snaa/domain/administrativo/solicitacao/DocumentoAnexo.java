package br.edu.ifam.snaa.domain.administrativo.solicitacao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="documento_anexo")
/**
 *  
 * @author Ronei
 * 
 * Entidade que armazena o documento anexado na
 * solicitação de criação de conta
 *
 */
public class DocumentoAnexo implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	private byte[] arquivo;
	
	@OneToOne
	private SolicitacaoConta solicitacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	public DocumentoAnexo() {
	}
	
	public DocumentoAnexo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public SolicitacaoConta getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoConta solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	
	
	

}
