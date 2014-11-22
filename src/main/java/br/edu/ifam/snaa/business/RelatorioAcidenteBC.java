package br.edu.ifam.snaa.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifam.snaa.domain.notificacao.TipoNotificacaoEnum;
import br.edu.ifam.snaa.domain.relatorio.RelatorioAcidente;
import br.edu.ifam.snaa.persistence.AcidenteDAO;
import br.edu.ifam.snaa.persistence.AcidenteNaoOficialDAO;
import br.edu.ifam.snaa.view.dto.AcidenteDTO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public class RelatorioAcidenteBC  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private AcidenteDAO acidenteDAO;
	@Inject
	private AcidenteNaoOficialDAO acidenteNaoOficialDAO;
	
	
	
	public List<RelatorioAcidente> pesquisar(AcidenteDTO acidente) {
		
		List<RelatorioAcidente> lista = new ArrayList<RelatorioAcidente>();
		
		if (acidente.getTipoNotificacao()  == null || TipoNotificacaoEnum.NAO_OFICIAL.equals(acidente.getTipoNotificacao())) {
			lista.addAll(this.acidenteNaoOficialDAO.pesquisarRelatorio(acidente));
		}
		
		if (acidente.getTipoNotificacao()  == null || TipoNotificacaoEnum.POR_UNIDADE_SAUDE.equals(acidente.getTipoNotificacao())) {
			lista.addAll(this.acidenteDAO.pesquisarRelatorio(acidente));
		}
		
		
		return lista;
	}
	
	
	
	

}
