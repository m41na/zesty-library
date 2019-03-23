package com.practicaldime.library.dao;

import java.util.List;

import com.practicaldime.library.entity.Book;

public interface BookRepository {
	
	void save(Book book);
	
	Book findOne(Long id);

	List<Book> findAll();
	
	int delete(Long id);
	
	int update(int pages, Long id);
	
	Long count();
}