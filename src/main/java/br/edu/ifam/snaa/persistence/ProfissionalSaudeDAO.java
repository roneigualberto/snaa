package br.edu.ifam.snaa.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.notificacao.oficial.ProfissionalSaude;
import br.edu.ifam.snaa.domain.notificacao.oficial.UnidadeSaude;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class ProfissionalSaudeDAO extends GenericDAO<ProfissionalSaude, Long> {

	private static final long serialVersionUID = 1L;

	public ProfissionalSaude buscarAtivo(Usuario usuario) {
		String sql = "select p from ProfissionalSaude p where p.usuario = :usuario and p.status = :status";

		return (ProfissionalSaude) createQuery(sql)
				.setParameter("usuario", usuario)
				.setParameter("status", StatusRegistro.ATIVO).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<ProfissionalSaude> obterProfissionaisDaUnidade(
			UnidadeSaude unidadeSaude) {

		String sql = "select p from ProfissionalSaude p where p.unidadeSaude = :unidade";

		return createQuery(sql).setParameter("unidade", unidadeSaude)
				.setParameter("status", StatusRegistro.ATIVO).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ProfissionalSaude> pesquisar(ProfissionalSaude profissionalSaude) {
		StringBuilder hql = new StringBuilder(
				"select p from ProfissionalSaude p  where 1 = 1 ");

		Map<String, Object> parametros = buildParametros(hql, profissionalSaude);
		Query query = createQuery(hql, parametros);

		return query.getResultList();
	}

	private Map<String, Object> buildParametros(StringBuilder hql,
			ProfissionalSaude profissional) {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		Usuario usuario = profissional.getUsuario();

		hql.append(" and p.unidadeSaude = :unidade ");
		parametros.put("unidade", profissional.getUnidadeSaude());

		if (Util.isNotNull(usuario.getCpf())) {
			hql.append(" and p.usuario.cpf = :cpf ");
			parametros.put("cpf", usuario.getCpf());
		}

		if (Util.isNotNull(usuario.getRg())) {
			hql.append("and upper(p.usuario.rg) like upper(:rg) ");
			parametros.put("rg", "%" + usuario.getRg() + "%");
		}

		if (Util.isNotNull(usuario.getNome())) {
			hql.append("and upper(p.usuario.nome) like upper(:nome) ");
			parametros.put("nome", "%" + usuario.getNome() + "%");
		}

		if (usuario.getDataNascimento() != null) {
			hql.append("and usuario.dataNascimento = :dataNascimento ");
			parametros.put("dataNascimento", usuario.getDataNascimento());
		}

		if (usuario.getSexo() != null) {
			hql.append("and p.usuario.sexo = :sexo ");
			parametros.put("sexo", usuario.getSexo());
		}

		return parametros;

	}

}
