package com.practicaldime.library.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.practicaldime.library.dao.AuthorRepository;
import com.practicaldime.library.dao.BookRepository;
import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;

public class Query implements GraphQLQueryResolver {

	private BookRepository bookRepository;
	private AuthorRepository authorRepository;

	public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	public Iterable<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public long countBooks() {
		return bookRepository.count();
	}

	public long countAuthors() {
		return authorRepository.count();
	}
}
