package br.edu.ifam.snaa.view.dto;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.DadosRepresentante;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.DadosUnidade;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.DocumentoAnexo;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.SolicitacaoConta;
import br.edu.ifam.snaa.domain.endereco.Bairro;
import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.edu.ifam.snaa.util.Util;

public class SolicitacaoContaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoConta solicitacao;

	private UploadedFile arquivo;

	private List<Bairro> bairros;

	private String confirmacaoSenha;

	public DadosUnidade getUnidade() {
		return solicitacao.getUnidade();
	}

	public void setUnidade(DadosUnidade unidade) {
		solicitacao.setUnidade(unidade);
	}

	public DadosRepresentante getRepresentante() {
		return solicitacao.getRepresentante();
	}

	public void setRepresentante(DadosRepresentante representante) {
		solicitacao.setRepresentante(representante);
	}

	public StatusRegistro getSituacaoRegistro() {
		return solicitacao.getStatus();
	}

	public void setSituacaoRegistro(StatusRegistro situacaoRegistro) {
		solicitacao.setStatus(situacaoRegistro);
	}

	public int hashCode() {
		return solicitacao.hashCode();
	}

	public boolean equals(Object obj) {
		return solicitacao.equals(obj);
	}

	public String toString() {
		return solicitacao.toString();
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public SolicitacaoConta build() {

		if (arquivo == null) {
			throw new NullPointerException();

		}
		solicitacao.setDocumento(new DocumentoAnexo(arquivo.getContents()));
		
		Bairro bairro  = solicitacao.getUnidade().getEndereco().getBairro();
		
		solicitacao.getUnidade().getEndereco().getLogradouro().setBairro(bairro);

		Localizacao localizacao = solicitacao.getUnidade().getEndereco()
				.getLocalizacao();

		if (localizacao == null || Util.isNull(localizacao.getDescricao())) {
			solicitacao.getUnidade().getEndereco().setLocalizacao(null);
		}
	

		return solicitacao;

	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public boolean isNotEmptyArquivo() {
		return this.arquivo != null;
	}

}
