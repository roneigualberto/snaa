package br.edu.ifam.snaa.view;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.edu.ifam.snaa.domain.administrativo.solicitacao.DocumentoAnexo;

@RequestScoped
@Named
public class DocumentoAnexoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentoAnexo documentoAnexo;

	public DocumentoAnexo getDocumentoAnexo() {
		return documentoAnexo;
	}

	public void setDocumentoAnexo(DocumentoAnexo documentoAnexo) {
		this.documentoAnexo = documentoAnexo;
	}

	public StreamedContent getContent() {
		if (this.documentoAnexo == null) {
			return null;
		}
		return new DefaultStreamedContent(new ByteArrayInputStream(
				this.documentoAnexo.getArquivo()), "image/jpeg");
	}
}
