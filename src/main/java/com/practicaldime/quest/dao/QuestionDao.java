package com.practicaldime.quest.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.practicaldime.quest.entity.QueFilter;
import com.practicaldime.quest.entity.Question;

@Repository
public class QuestionDao implements QuestionRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Question que) {
		que.setCreatedOn(new Date());
		em.persist(que);
	}

	@Override
	public Question findOne(Long id) {
		return em.find(Question.class, id);
	}

	@Override
	public List<Question> findAll(QueFilter filter) {
		//String queryString = "SELECT q FROM Question q JOIN q.createdBy a where a.id = :id";
		String queryString = "SELECT q FROM Question q JOIN q.createdBy a";
		
		Map<String, Object> where = new HashMap<>();
		if(filter.getAuthorId() != null) {
			where.put("a.id", filter.getAuthorId());
		}
		if(filter.getFrom() != null) {
			where.put("q.createdOn", filter.getStart());
		}
		if(filter.getUntil()!= null) {
			where.put("q.createdOn", filter.getUntil());
		}
		if(filter.getContent() != null) {
			where.put("q.content", filter.getContent());
		}
		
		String whereString = " WHERE ";
		List<String> keys = new ArrayList<>(where.keySet());
		for(int i = 0; i < keys.size(); i++) {
			String param = keys.get(i);
			whereString += String.format("%s=%s", keys.get(i), String.format(":%s", param.substring(param.indexOf(".") + 1)));
			if(i + 1 < keys.size()) {
				whereString += " AND ";
			}
		}
		
		if(whereString.length() > " WHERE ".length()) {
			queryString += whereString;
		}
		
		TypedQuery<Question> criteria = em.createQuery(queryString, Question.class);
		for(Iterator<String> iter = where.keySet().iterator(); iter.hasNext();) {
			String param = iter.next();
			criteria.setParameter(param.substring(param.indexOf(".") + 1), where.get(param));
			
		}
		
		if(filter.getLimit() != null) {
			criteria.setMaxResults(filter.getLimit()); 
		} 
		if(filter.getStart() != null) {
			criteria.setFirstResult(filter.getStart());
		}
			
		return criteria.getResultList();
	}

	@Override
	public int delete(Long id) {
		Query query = em.createQuery("DELETE FROM Question q where q.id = :id");
		int deletedCount = query.setParameter("id", id).executeUpdate();
		return deletedCount;
	}

	@Override
	public Question update(Question que) {
		return em.merge(que);
	}
	
	@Override
	public Long count(Long authordId) {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Question q JOIN q.createdBy a where a.id = :id", Long.class);
		Long count = query.setParameter("id", authordId).getSingleResult();
		return count;
	}
}