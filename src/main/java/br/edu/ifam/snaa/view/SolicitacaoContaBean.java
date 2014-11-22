package br.edu.ifam.snaa.view;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.validator.constraints.impl.EmailValidator;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.slf4j.Logger;

import br.edu.ifam.snaa.business.BairroBC;
import br.edu.ifam.snaa.business.LogradouroBC;
import br.edu.ifam.snaa.business.SolicitacaoContaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.DadosRepresentante;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.SolicitacaoConta;
import br.edu.ifam.snaa.domain.endereco.Bairro;
import br.edu.ifam.snaa.domain.endereco.Logradouro;
import br.edu.ifam.snaa.domain.endereco.TipoLogradouro;
import br.edu.ifam.snaa.exception.ApplicationException;
import br.edu.ifam.snaa.util.Util;
import br.edu.ifam.snaa.view.dto.SolicitacaoContaDTO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Beans;

@ViewController
//@RequiredRole(Constantes.PERFIL_ADMINISTRADOR)
public class SolicitacaoContaBean extends AbstractPageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoContaBC solicitacaoContaBC;

	@Inject
	private LogradouroBC logradouroBC;

	@Inject
	private Logger logger;

	@Inject
	private SolicitacaoContaDTO solicitacaoContaDTO;

	private TipoLogradouro tipoLogradouro;

	private String telefone;

	private String email;

	@Inject
	private BairroBC bairroBC;

	@Inject
	private MessageContext messageContext;

	public SolicitacaoContaDTO getSolicitacaoConta() {
		return solicitacaoContaDTO;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public boolean validarTelefone() {

		if (Util.isNull(telefone)) {
			messageContext.add("Informe o telefone: ", SeverityType.ERROR);
			return false;
		}
		return true;
	}

	public void addTelefone() {

		if (validarTelefone()) {
			solicitacaoContaDTO.getUnidade().addTelefone(telefone);
			telefone = null;
		}

	}

	public void removeTelefone() {
		solicitacaoContaDTO.getUnidade().removeTelefone(telefone);
		this.telefone = null;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void changeMunicipio() {

		List<Bairro> bairros = bairroBC.buscarPorMunicipio(solicitacaoContaDTO
				.getUnidade().getMunicipio());

		this.solicitacaoContaDTO.setBairros(bairros);
	}

	public List<Logradouro> handleAutoCompleteLogradouro(String nome) {
		Logradouro logradouro = new Logradouro(nome);
		logradouro.setBairro(solicitacaoContaDTO.getUnidade().getEndereco()
				.getBairro());
		logradouro.setTipoLogradouro(tipoLogradouro);

		return logradouroBC.buscar(logradouro);
	}

	public String onFlowProcess(FlowEvent event) {
		logger.info(event.getNewStep());

		return event.getNewStep();

	}

	@Transactional
	public void salvar() {

		if (validarSolicitacao()) {
			SolicitacaoConta solicitacaoConta = solicitacaoContaDTO.build();
			solicitacaoConta.getUnidade().getEndereco().getLogradouro().setTipoLogradouro(tipoLogradouro);
			solicitacaoContaBC.insert(solicitacaoConta);

			this.solicitacaoContaDTO = Beans
					.getReference(SolicitacaoContaDTO.class);
			messageContext.add("Solicitação enviada com sucesso",
					SeverityType.INFO);
		}

	}

	@ExceptionHandler
	public void tratar(ApplicationException ex) {
		messageContext.add(ex.getMessage(), SeverityType.ERROR);
	}

	public DadosRepresentante getRepresentante() {

		return this.solicitacaoContaDTO.getRepresentante();
	}

	public void handleFileUpload(FileUploadEvent event) {
		solicitacaoContaDTO.setArquivo(event.getFile());
	}

	public void cancelarUpload() {

		this.solicitacaoContaDTO.setArquivo(null);
	}

	private boolean validarSolicitacao() {
		boolean result = true;
		EmailValidator emailValidator = new EmailValidator();
		
		if (solicitacaoContaDTO.getArquivo() == null) {
			messageContext.add("Documento com Foto Não foi informado",
					SeverityType.ERROR);
			result = false;
		}

		if (!emailValidator.isValid(solicitacaoContaDTO.getRepresentante().getEmail(), null)) {
			messageContext.add("E-mail do representante é inválido",
					SeverityType.ERROR);
			result = false;
		}
		
		if (!solicitacaoContaDTO.getRepresentante().getSenha().equals(solicitacaoContaDTO.getConfirmacaoSenha())) {
			messageContext.add("Senha e Confirmação não coincidem",
					SeverityType.ERROR);
			result = false;
		}

		return result;
	}

}