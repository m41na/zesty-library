package com.practicaldime.library.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;
import com.practicaldime.library.service.LibraryService;

public class Query implements GraphQLQueryResolver {

	private LibraryService service;

	public Query(LibraryService service) {
		this.service = service;
	}

	public Iterable<Book> findAllBooks() {
		return service.listBooks();
	}

	public Iterable<Author> findAllAuthors() {
		return service.listAuthors();
	}

	public long countBooks() {
		return service.countBooks();
	}

	public long countAuthors() {
		return service.countAuthors();
	}
}
