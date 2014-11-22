package br.edu.ifam.snaa.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.edu.ifam.snaa.domain.StatusRegistro;
import br.edu.ifam.snaa.domain.administrativo.Administrador;
import br.edu.ifam.snaa.domain.seguranca.Usuario;
import br.edu.ifam.snaa.util.Util;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class AdministradorDAO extends GenericDAO<Administrador, Long> {

	private static final long serialVersionUID = 1L;

	public Administrador buscarAtivo(Usuario usuario) {
		String sql = "select a from Administrador a where a.usuario = :usuario and a.status = :status";

		return (Administrador) createQuery(sql)
				.setParameter("usuario", usuario)
				.setParameter("status", StatusRegistro.ATIVO).getSingleResult();

	}

	@SuppressWarnings("unchecked")
	public List<Administrador> pesquisar(Administrador administrador) {
		StringBuilder hql = new StringBuilder(
				"select a from Administrador a  where 1 = 1 ");

		Map<String, Object> parametros = buildParametros(hql, administrador);
		Query query = createQuery(hql, parametros);

		return query.getResultList();
	}

	private Map<String, Object> buildParametros(StringBuilder hql,
			Administrador administrador) {
		Map<String, Object> parametros = new LinkedHashMap<String, Object>();

		Usuario usuario = administrador.getUsuario();

		if (Util.isNotNull(usuario.getCpf())) {
			hql.append(" and a.usuario.cpf = :cpf ");
			parametros.put("cpf", usuario.getCpf());
		}

		if (Util.isNotNull(usuario.getRg())) {
			hql.append("and upper(a.usuario.rg) like upper(:rg) ");
			parametros.put("rg", "%" + usuario.getRg() + "%");
		}

		if (Util.isNotNull(usuario.getNome())) {
			hql.append("and upper(a.usuario.nome) like upper(:nome) ");
			parametros.put("nome", "%" + usuario.getNome() + "%");
		}

		if (usuario.getDataNascimento() != null) {
			hql.append("and a.usuario.dataNascimento = :dataNascimento ");
			parametros.put("dataNascimento", usuario.getDataNascimento());
		}

		if (usuario.getSexo() != null) {
			hql.append("and a.usuario.sexo = :sexo ");
			parametros.put("sexo", usuario.getSexo());
		}

		return parametros;

	}

}
