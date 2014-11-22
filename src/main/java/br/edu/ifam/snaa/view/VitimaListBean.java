package br.edu.ifam.snaa.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.model.DataModel;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.edu.ifam.snaa.business.VitimaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.domain.notificacao.oficial.Vitima;
import br.edu.ifam.snaa.security.UsuarioSessao;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@RequiredRole(Constantes.PERFIL_PROFISSIONAL_SAUDE)
public class VitimaListBean extends AbstractListPageBean<Vitima, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private VitimaBC vitimaBC;

	@Inject
	private Vitima vitima;

	@Inject
	private UsuarioSessao usuarioSessao;

	private List<Vitima> vitimas = new ArrayList<Vitima>();
	
	private LazyDataModel<Vitima> dataModel;

	@PostConstruct
	public void iniciar() {
		pesquisar();
	}

	@Override
	protected List<Vitima> handleResultList() {

		return vitimas;
	}

	public Vitima getVitima() {
		return vitima;
	}

	@SuppressWarnings("serial")
	public void pesquisar() {
		
		dataModel = new LazyDataModel<Vitima>() {
			
			
			
			@Override
			public List<Vitima> load(int inicio, int pageSize,
					String campoOrdenacao, SortOrder sentidoOrdenacao,
					Map<String, String> filtros) {
				
				Pagination paginacao = getPagination();
				
				paginacao.setPageSize(pageSize);
				paginacao.setFirstResult(inicio);
				 

				if (semFiltros(vitima)) {
					ProfissionalSaude profissionalSaude = usuarioSessao
							.getProfissionalSaude();
					vitima.setProfissionalSaude(profissionalSaude);
				} 
				else {
					vitima.setProfissionalSaude(null);
				}
				
				
				vitimas = vitimaBC.pesquisar(vitima);
				
				
				dataModel.setRowCount(paginacao.getTotalResults());
				
				return vitimas;
				
			}
		};
		
		
		

	}

	public DataModel<Vitima> getVitimas() {
		return dataModel;
	}

	private boolean semFiltros(Vitima vitima) {

		return Util.isNull(vitima.getCpf()) && Util.isNull(vitima.getRg())
				&& Util.isNull(vitima.getNome())
				&& vitima.getDataNascimento() == null
				&& vitima.getSexo() == null;

		

	}

}