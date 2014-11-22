package br.edu.ifam.snaa.persistence;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifam.snaa.domain.endereco.Localizacao;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;

@PersistenceController
public class LocalizacaoDAO extends GenericDAO<Localizacao, Long> {

	private static final long serialVersionUID = 1L;
	
	
	public List<Localizacao> buscar(Localizacao localizacao) {
		Map<String,Object> parametros = new LinkedHashMap<String,Object>();
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select localizacao from Localizacao localizacao")
		   .append(" where upper(localizacao.descricao) like upper(:nome) ");
		parametros.put("nome","%"+localizacao.getDescricao().trim()+"%");
		
		
		if(localizacao.getMunicipio() !=null && 
					localizacao.getMunicipio().getId() != null) {
			hql.append(" and localizacao.municipio = :municipio");
			parametros.put("municipio",localizacao.getMunicipio());
		}
		
		
		if(localizacao.getTipoLocalizacao() != null && 
					localizacao.getTipoLocalizacao().getId() != null) {
			hql.append(" and localizacao.tipoLocalizacao = :tipoLocalizacao");
			parametros.put("tipoLocalizacao",localizacao.getTipoLocalizacao());
		}
		
		return getResultList(hql, parametros);
		
	}
	

}
