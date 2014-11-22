package br.edu.ifam.snaa.persistence;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;

public class GenericDAO<T,I> extends JPACrud<T, I> {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@SuppressWarnings("unchecked")
	public List<T> getResultList(String hql,Map<String,Object> parametros) {
		Query query = createQuery(hql);
		
		montarParametros(query, parametros);
		
		return query.getResultList();
		
	}
	
	
	
	
	
	public List<T> getResultList(StringBuilder hql,Map<String,Object> parametros) {
		return getResultList(hql.toString(), parametros);
	}
	
	
	protected Query createQuery(String hql,Map<String,Object> parametros) {
		Query query = createQuery(hql);
		
		montarParametros(query, parametros);
		
		return query;
	}
	
	
	protected Query createQuery(StringBuilder hql,Map<String,Object> parametros) {
		Query query = createQuery(hql.toString());
		
		montarParametros(query, parametros);
		
		return query;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public T getSingleResult(String hql,Map<String,Object> parametros) {
		Query query = createQuery(hql);
		
		montarParametros(query, parametros);
		
		return (T) query.getSingleResult();
		
	}
	

	public T getSingleResult(StringBuilder hql,Map<String,Object> parametros) {
		return getSingleResult(hql.toString(), parametros);
	}
	
	
	
	protected void montarParametros(final Query query,Map<String,Object> parametros) {
		
		for (Map.Entry<String, Object> parametro: parametros.entrySet()) {
			query.setParameter(parametro.getKey(),parametro.getValue());
		}
	}

}
