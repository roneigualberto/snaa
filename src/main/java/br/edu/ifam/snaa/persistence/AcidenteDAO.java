package br.edu.ifam.snaa.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.notificacao.SituacaoAcidenteEnum;
import br.edu.ifam.snaa.domain.notificacao.oficial.Acidente;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.domain.relatorio.RelatorioAcidente;
import br.edu.ifam.snaa.view.dto.AcidenteDTO;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class AcidenteDAO extends GenericDAO<Acidente, Long> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Acidente> buscarPor(SituacaoAcidenteEnum situacao,
			ProfissionalSaude profissionalSaude) {

		String sql = "select a from Acidente a join fetch a.ultimoAtendimento   join fetch a.vitima where a.situacao = :situacao and a.profissionalSaude = :prof";
		Query query = createQuery(sql);

		Pagination pagination = getPagination();

		if (pagination != null) {

			pagination.setTotalResults(rowCountPendentes(situacao,
					profissionalSaude));

			query.setFirstResult(pagination.getFirstResult());

			query.setMaxResults(pagination.getPageSize());

		}

		query.setParameter("situacao", situacao).setParameter("prof",
				profissionalSaude);

		return query.getResultList();
	}

	private int rowCountPendentes(SituacaoAcidenteEnum situacao,
			ProfissionalSaude prof) {

		StringBuilder hql = new StringBuilder(
				"select count(a) from Acidente a where a.situacao = :situacao and a.profissionalSaude = :prof");

		Query query = createQuery(hql.toString());

		query.setParameter("prof", prof).setParameter("situacao", situacao);

		return ((Long) query.getSingleResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	public List<RelatorioAcidente> pesquisarRelatorio(AcidenteDTO acidente) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder(
				"select new br.edu.ifam.snaa.domain.relatorio.RelatorioAcidente(0,municipio.nome,periodo, vitima.sexo.sexo,quadro.descricao,count(a))"
						+ " from Acidente a "
						+ "join a.municipio as municipio "
						+ "join a.periodo as periodo "
						+ "join a.vitima as vitima "
						+ "inner join a.quadroSaida as quadro where 1 = 1 ");

		if (acidente.getMunicipio() != null) {
			hql.append(" and a.municipio = :municipio ");
			parametros.put("municipio", acidente.getMunicipio());
		}

		if (acidente.getPeriodo() != null) {
			hql.append(" and a.periodo = :periodo ");
			parametros.put("periodo", acidente.getPeriodo());
		}

		if (acidente.getQuadroSaida() != null) {
			hql.append(" and a.quadroSaida = :quadroSaida ");
			parametros.put("quadroSaida", acidente.getQuadroSaida());
		}

		if (acidente.getSexo() != null) {
			hql.append(" and a.vitima.sexo = :sexo ");
			parametros.put("sexo", acidente.getSexo());
		}

		if (acidente.getDataInicio() != null && acidente.getDataFim() == null) {
			hql.append("and a.dataOcorrencia >= :dataInicio ");
			parametros.put("dataInicio", acidente.getDataInicio());

		} else if (acidente.getDataInicio() == null
				&& acidente.getDataFim() != null) {
			hql.append(" a.dataOcorrencia <= :dataFim ");
			parametros.put("dataFim", acidente.getDataFim());
		} else if (acidente.getDataInicio() != null
				&& acidente.getDataFim() != null) {
			hql.append(" and (a.dataOcorrencia >= :dataInicio and a.dataOcorrencia <= :dataFim)  ");
			parametros.put("dataInicio", acidente.getDataInicio());
			parametros.put("dataFim", acidente.getDataFim());
		}

		hql.append(" group by a");

		Query query = createQuery(hql.toString());

		this.montarParametros(query, parametros);

		return query.getResultList();
	}

	public Acidente loadAcidente(Long id) {
		
		return (Acidente) createQuery(
				"select a from  Acidente a join fetch a.atendimentos where a.id = :id ")
				.setParameter("id", id).getSingleResult();
	}
}
