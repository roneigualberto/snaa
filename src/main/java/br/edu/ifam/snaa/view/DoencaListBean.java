package br.edu.ifam.snaa.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.edu.ifam.snaa.business.DoencaBC;
import br.edu.ifam.snaa.constant.Constantes;
import br.edu.ifam.snaa.domain.notificacao.oficial.Doenca;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.security.RequiredRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;

@ViewController
@NextView("/pages/administrativo/cadastros/doenca/doencaForm.jsf")
@PreviousView("/pages/administrativo/cadastros/doenca/doencaList.jsf")
@RequiredRole({Constantes.PERFIL_ADMINISTRADOR,Constantes.PERFIL_PROFISSIONAL_SAUDE})
public class DoencaListBean extends AbstractListPageBean<Doenca, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private DoencaBC doencaBC;
	
	private List<Doenca> doencas;
	
	@Inject
	private Doenca doenca;
	
	@Override
	protected List<Doenca> handleResultList() {
		
		if (doencas == null) {
			this.doencas =  this.doencaBC.findAll();
		}
		return this.doencas;
	}
	
	public void pesquisar() {
		this.clear();
		this.doencas = this.doencaBC.pesquisar(doenca);
	}
	
		
	
	public Map<String,String> getDoencas() {
		List<Doenca> doencas = handleResultList();
		Map<String,String> mapDoencas  = new HashMap<String, String>();
		
		for(Doenca doenca : doencas) {
			mapDoencas.put(doenca.getNome(), doenca.getId().toString());
		}
		
		
		return mapDoencas;
	}
	
	public Doenca getDoenca() {
		return doenca;
	}

}