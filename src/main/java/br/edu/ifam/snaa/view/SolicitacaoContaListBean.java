package br.edu.ifam.snaa.view;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifam.snaa.business.EmailBC;
import br.edu.ifam.snaa.business.SolicitacaoContaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.administrativo.solicitacao.SolicitacaoConta;
import br.edu.ifam.snaa.exception.ApplicationException;
import br.edu.ifam.snaa.security.UsuarioSessao;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
public class SolicitacaoContaListBean extends
		AbstractListPageBean<SolicitacaoConta, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SolicitacaoContaBC solicitacaoContaBC;

	private LazyDataModel<SolicitacaoConta> dataModel;

	@Inject
	private SolicitacaoConta solicitacao;

	@Inject
	private MessageContext messageContext;

	@Inject
	private EmailBC emailBC;
	
	@Inject
	private UsuarioSessao usuarioSessao;

	@PostConstruct
	public void init() {
		this.dataModel = new LazyDataModel<SolicitacaoConta>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public List<SolicitacaoConta> load(int inicio, int pageSize,
					String campoOrdenacao, SortOrder sentidoOrdenacao,
					Map<String, String> filtros) {

				Pagination paginacao = getPagination();

				paginacao.setPageSize(pageSize);
				paginacao.setFirstResult(inicio);

				List<SolicitacaoConta> pendentes = solicitacaoContaBC
						.obterPendentes();

				dataModel.setRowCount(paginacao.getTotalResults());

				return pendentes;

			}
		};
	}

	@Override
	protected List<SolicitacaoConta> handleResultList() {
		return this.solicitacaoContaBC.findAll();
	}

	public LazyDataModel<SolicitacaoConta> getPendentes() {

		return dataModel;
	}

	public SolicitacaoConta getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoConta solicitacao) {
		this.solicitacao = solicitacao;
		consultarSolicitacao();
	}

	public void consultarSolicitacao() {

		this.solicitacao = this.solicitacaoContaBC.load(this.solicitacao
				.getId());

	}
	
	@RequiredRole(value = Constantes.PERFIL_ADMINISTRADOR)
	public void aprovar() {
		solicitacao.setAvaliador(usuarioSessao.getAdministrador());

		this.solicitacaoContaBC.aprovar(solicitacao);
		messageContext.add("Solicitacao aprovada com sucesso.Em breve será enviado um e-mail para o solicitante",
				SeverityType.INFO);
		
		
		 Util.execute(new Runnable() {
				
				@Override
				public void run() {
					emailBC.enviarRespostaAprovacao(solicitacao.getRepresentante()
							.getEmail(), solicitacao.getMensagem());

				}
			});
	
	}
	
	@RequiredRole(value = Constantes.PERFIL_ADMINISTRADOR)
	public void reprovar() {

		solicitacao.setAvaliador(usuarioSessao.getAdministrador());
		this.solicitacaoContaBC.reprovar(solicitacao);
		
		
        Util.execute(new Runnable() {
			
			@Override
			public void run() {
				emailBC.enviarRespostaReprovacao(solicitacao.getRepresentante()
						.getEmail(), solicitacao.getMensagem());
			}
		});
        
        messageContext.add("Solicitacao reprovada com sucesso. Em breve será enviado um e-mail para o solicitante",
				SeverityType.INFO);
		

	}

	@ExceptionHandler
	public void tratarErro(ApplicationException ex) {
		messageContext
				.add("Unidade de saúde ou representante de unidade já existem no sistema",
						SeverityType.ERROR);
	}

}