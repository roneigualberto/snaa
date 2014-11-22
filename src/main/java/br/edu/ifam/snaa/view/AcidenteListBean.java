package br.edu.ifam.snaa.view;

import static br.edu.ifam.snaa.constant.Constantes.PERFIL_PROFISSIONAL_SAUDE;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifam.snaa.business.AcidenteBC;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.edu.ifam.snaa.security.UsuarioSessao;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@NextView("/pages/profissionalsaude/acidente/acidenteForm.jsf")
@PreviousView("/pages/profissionalsaude/atendimentoList.jsf")
@RequiredRole(value = PERFIL_PROFISSIONAL_SAUDE)
public class AcidenteListBean extends AbstractListPageBean<Acidente, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AcidenteBC acidenteBC;

	private LazyDataModel<Acidente> dataModel;

	@Inject
	private UsuarioSessao usuarioSessao;

	@SuppressWarnings("serial")
	@PostConstruct
	public void init() {

		dataModel = new LazyDataModel<Acidente>() {

			@Override
			public List<Acidente> load(int inicio, int pageSize,
					String campoOrdenacao, SortOrder sentidoOrdenacao,
					Map<String, String> filtros) {

				Pagination paginacao = getPagination();

				paginacao.setPageSize(pageSize);
				paginacao.setFirstResult(inicio);

				List<Acidente> pendentes = acidenteBC
						.listPendentesPorProfissional(usuarioSessao
								.getProfissionalSaude());

				dataModel.setRowCount(paginacao.getTotalResults());

				return pendentes;

			}
		};

	}

	@Override
	protected List<Acidente> handleResultList() {
		return this.acidenteBC.findAll();
	}

	public LazyDataModel<Acidente> getPendentes() {

		return dataModel;
	}

}