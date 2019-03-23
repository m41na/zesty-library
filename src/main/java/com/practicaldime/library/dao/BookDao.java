package com.practicaldime.library.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.practicaldime.library.entity.Book;

@Repository
public class BookDao implements BookRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Book book) {
		em.persist(book);
	}

	@Override
	public Book findOne(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

	@Override
	public int delete(Long id) {
		Query query = em.createQuery("DELETE FROM Book b WHERE b.id = :id");
		int deletedCount = query.setParameter("id", id).executeUpdate();
		return deletedCount;
	}

	@Override
	public int update(int pages, Long id) {
		Query query = em.createQuery("UPDATE Book SET pageCount = :pages where id = :id");
		int updateCount = query.setParameter("pages", pages).setParameter("id", id).executeUpdate();
		return updateCount;
	}
	
	@Override
	public Long count() {
		TypedQuery<Long> query = em.createQuery("SELECT count(*) FROM Book c", Long.class);
		return query.getSingleResult();
	}
}