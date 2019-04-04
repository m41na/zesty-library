package com.practicaldime.quest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.practicaldime.quest.entity.Location;

@Repository
public class LocationDao implements LocationRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Location loc) {
		em.persist(loc);
	}

	@Override
	public Location findOne(Long id) {
		return em.find(Location.class, id);
	}

	@Override
	public List<Location> findAll() {
		TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
		return query.getResultList();
	}

	@Override
	public Long count() {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Location l", Long.class);
		return query.getSingleResult();
	}
}