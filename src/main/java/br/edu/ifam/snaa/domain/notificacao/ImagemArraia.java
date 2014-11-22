package br.edu.ifam.snaa.domain.notificacao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagem_arraia")
public class ImagemArraia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Arraia arraia;

	@OneToOne(mappedBy = "imagemArraia", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private ArquivoImagemArraia arquivo;

	public ImagemArraia() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Arraia getArraia() {
		return arraia;
	}

	public void setArraia(Arraia arraia) {
		this.arraia = arraia;
	}

	public ArquivoImagemArraia getArquivo() {
		return arquivo;
	}

	public void setArquivo(ArquivoImagemArraia arquivo) {
		this.arquivo = arquivo;
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
		ImagemArraia other = (ImagemArraia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public ImagemArraia(byte[] bytes) {
		this.arquivo = new ArquivoImagemArraia(bytes,this);
	}


}
