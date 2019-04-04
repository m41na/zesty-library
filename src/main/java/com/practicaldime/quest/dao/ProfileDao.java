package com.practicaldime.quest.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.practicaldime.quest.entity.Profile;

@Repository
public class ProfileDao implements ProfileRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Profile author) {
		author.setCreatedOn(new Date());
		em.persist(author);
	}

	@Override
	public Profile findOne(Long id) {
		return em.find(Profile.class, id);
	}

	@Override
	public List<Profile> findAll() {
		TypedQuery<Profile> query = em.createQuery("SELECT p FROM Profile p", Profile.class);
		return query.getResultList();
	}

	@Override
	public Long count() {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Profile p", Long.class);
		return query.getSingleResult();
	}
}