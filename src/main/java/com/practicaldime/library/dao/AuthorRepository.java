package com.practicaldime.library.dao;

import java.util.List;

import com.practicaldime.library.entity.Author;

public interface AuthorRepository {

	void save(Author author);
	
	Author findOne(Long id);

	List<Author> findAll();
	
	Long count();
}