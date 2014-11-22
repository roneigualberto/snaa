package br.edu.ifam.snaa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.notificacao.naooficial.AcidenteNaoOficial;
import br.edu.ifam.snaa.domain.relatorio.RelatorioAcidente;
import br.edu.ifam.snaa.view.dto.AcidenteDTO;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class AcidenteNaoOficialDAO extends GenericDAO<AcidenteNaoOficial, Long> {

	private static final long serialVersionUID = 1L;


	
	
	@SuppressWarnings("unchecked")
	public List<RelatorioAcidente> pesquisarRelatorio(
			AcidenteDTO acidente) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				"select new br.edu.ifam.snaa.domain.relatorio.RelatorioAcidente(1,municipio.nome,periodo, vitima.sexo.sexo,'',count(a))"
				+ " from AcidenteNaoOficial a "
				+ "join a.municipio as municipio "
				+ "join a.periodo as periodo "
				+ "join a.vitima as vitima "
				+ "where 1 = 1 ");
		
		
		if (acidente.getMunicipio() != null) {
			hql.append(" and a.municipio = :municipio ");
			parametros.put("municipio", acidente.getMunicipio());
		}
		

		if (acidente.getPeriodo() != null) {
			hql.append(" and a.periodo = :periodo ");
			parametros.put("periodo", acidente.getPeriodo());
		}
		
		/*if (acidente.getQuadroSaida() != null) { 
			hql.append(" and a.quadroSaida = :quadroSaida ");
			parametros.put("quadroSaida", acidente.getQuadroSaida());
		}*/
		
		if (acidente.getSexo() != null) {
			hql.append(" and a.vitima.sexo = :sexo ");
			parametros.put("sexo", acidente.getSexo());
		}
		
		
		
		if (acidente.getDataInicio() != null && acidente.getDataFim() == null) {
			hql.append("and a.dataOcorrencia >= :dataInicio ");
			parametros.put("dataInicio", acidente.getDataInicio());
		
		} else if (acidente.getDataInicio() == null && acidente.getDataFim() != null) {
			hql.append(" a.dataOcorrencia <= :dataFim ");
			parametros.put("dataFim", acidente.getDataFim());
		}
		else if (acidente.getDataInicio() != null && acidente.getDataFim() != null) {
			hql.append(" and (a.dataOcorrencia >= :dataInicio and a.dataOcorrencia <= :dataFim)  ");
			parametros.put("dataInicio", acidente.getDataInicio());
			parametros.put("dataFim", acidente.getDataFim());
		}
		
		hql.append(" group by a");
		
		 Query query = createQuery(hql.toString());
		
		 this.montarParametros(query, parametros);
	

		return query.getResultList();
	}


}
