package br.edu.ifam.snaa.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifam.snaa.business.AcidenteBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.gov.frameworkdemoiselle.util.Parameter;

@Named
@RequestScoped
@RequiredRole(Constantes.PERFIL_PROFISSIONAL_SAUDE)
public class ConsultaAcidenteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Parameter<Long> idAcidente;

	@Inject
	@Path("reports/rel_acidente_detalhado.jasper")
	private Report relatorio;

	@Inject
	private FileRenderer fileRenderer;

	@Inject
	private AcidenteBC acidenteBC;

	@PostConstruct
	public void init() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext contexto = fc.getExternalContext();


		contexto.responseReset();
		Map<String, Object> parametros = new HashMap<String, Object>();

		List<Acidente> dados = new ArrayList<Acidente>();
		Acidente acidente = this.acidenteBC.loadAcidente(idAcidente.getValue());

		dados.add(acidente);
		byte[] buffer = relatorio.export(dados, parametros, Type.PDF);
		fileRenderer.render(buffer, FileRenderer.ContentType.PDF,
				"relatorio_acidente_detalhado.pdf");
		
		
	}

}
