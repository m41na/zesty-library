package com.practicaldime.library.dao;

import com.practicaldime.library.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Author findOne(Long id); 
	
}