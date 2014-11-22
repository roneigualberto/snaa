package br.edu.ifam.snaa.domain.notificacao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="arquivo_imagem_arraia")
public class ArquivoImagemArraia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(nullable=false)
	private byte[] imagem;
	
	@OneToOne
	@JoinColumn(name="imagem_arraia_id",nullable=false)
	private ImagemArraia imagemArraia;
		

	public ArquivoImagemArraia() {
		super();
	}

	public ArquivoImagemArraia(byte[] imagem) {
		super();
		this.imagem = imagem;
	}

	public ArquivoImagemArraia(byte[] imagem,ImagemArraia imagemArraia) {
		super();
		this.imagemArraia = imagemArraia;
		this.imagem = imagem;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public ImagemArraia getImagemArraia() {
		return imagemArraia;
	}

	public void setImagemArraia(ImagemArraia imagemArraia) {
		this.imagemArraia = imagemArraia;
	}

	
	
	
	
	
	
	
}
