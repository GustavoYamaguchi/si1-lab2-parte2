package dao;

import java.util.List;

import javax.persistence.Query;

import models.Meta;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

public class GenericDAOImpl {

	public boolean persist(Meta e) {
		JPA.em().persist(e);
		return true;
	}

	public void flush(){
		JPA.em().flush();
	}

	public void merge(Meta e) {
		JPA.em().merge(e);
	}
	
	public List<Meta> all() {
		String hql = "FROM Meta ORDER BY prioridade DESC";
		Query hqlQuery = JPA.em().createQuery(hql);
		return hqlQuery.getResultList();
	}
	
	public Query createQuery(String query) {
		return JPA.em().createQuery(query);
	}
	
	public void doneById(Long id){
		Meta meta = findByEntityId(id);
		meta.setSituacao("concluida");
		merge(meta);
	}
	
	public void removeById(Long id) {
		JPA.em().remove(findByEntityId(id));
	}

	public Meta findByEntityId(Long id) {
		return JPA.em().find(Meta.class, id);
	}
}
