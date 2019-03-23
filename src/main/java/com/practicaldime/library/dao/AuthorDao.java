package com.practicaldime.library.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.practicaldime.library.entity.Author;

@Repository
public class AuthorDao implements AuthorRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Author author) {
		em.persist(author);
	}

	@Override
	public Author findOne(Long id) {
		return em.find(Author.class, id);
	}

	@Override
	public List<Author> findAll() {
		TypedQuery<Author> query = em.createQuery("SELECT * FROM Author c", Author.class);
		return query.getResultList();
	}

	@Override
	public Long count() {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Author c", Long.class);
		return query.getSingleResult();
	}
}