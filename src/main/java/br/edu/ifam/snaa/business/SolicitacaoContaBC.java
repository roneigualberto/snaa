package br.edu.ifam.snaa.business;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.DocumentoAnexo;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.SolicitacaoConta;
import br.edu.ifam.snaa.domain.notificacao.oficial.RepresentanteUnidadeSaude;
import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;
import br.edu.ifam.snaa.exception.ApplicationException;
import br.edu.ifam.snaa.persistence.SolicitacaoContaDAO;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class SolicitacaoContaBC extends
		DelegateCrud<SolicitacaoConta, Long, SolicitacaoContaDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeSaudeBC unidadeBC;

	@Inject
	private DocumentoAnexoBC documentoAnexoBC;

	@Inject
	private UsuarioBC usuarioBC;

	@Inject
	private RepresentanteUnidadeSaudeBC representanteBC;

	@Inject
	private EmailBC emailBC;

	@Override
	public SolicitacaoConta insert(SolicitacaoConta solicitacao) {
		StatusRegistro status = StatusRegistro.PENDENTE;
		DocumentoAnexo documentoAnexo = solicitacao.getRepresentante()
				.getDocumento();
		solicitacao.setStatus(status);
		solicitacao.setDataEnvio(new Date());
		SolicitacaoConta solicitacaoConta = super.insert(solicitacao);

		documentoAnexo.setSolicitacao(solicitacaoConta);
		this.documentoAnexoBC.insert(documentoAnexo);

		return solicitacaoConta;
	}

	public List<SolicitacaoConta> obterPendentes() {

		return getDelegate().listarPorStatus(StatusRegistro.PENDENTE);
	}

	@Transactional
	public void aprovar(SolicitacaoConta solicitacaoConta) {
		UnidadeSaude unidadeSaude = solicitacaoConta.getUnidade()
				.buildUnidadeSaude();
		RepresentanteUnidadeSaude representante = solicitacaoConta
				.getRepresentante().buildRepresentante();

		unidadeSaude.setRepresentante(representante);

		unidadeBC.insert(unidadeSaude);

		SolicitacaoConta solicitacao = this.load(solicitacaoConta.getId());

		solicitacao.setStatus(StatusRegistro.ATIVO);
		solicitacao.setDataAvaliacao(new Date());

		getDelegate().update(solicitacao);

	}

	@Transactional
	public void reprovar(SolicitacaoConta solicitacaoConta) {

		solicitacaoConta.setStatus(StatusRegistro.INATIVO);
		solicitacaoConta.setDataAvaliacao(new Date());
		
		this.update(solicitacaoConta);

	}

	@ExceptionHandler
	public void tratarErro(PersistenceException ex) {
		throw new ApplicationException("Erro");
	}

}
