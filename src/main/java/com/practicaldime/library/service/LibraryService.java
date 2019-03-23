package com.practicaldime.library.service;

import java.util.List;

import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;

public interface LibraryService {

	//Authors
	void save(Author author);

	Author findAuthor(Long id);

	List<Author> listAuthors();

	Long countAuthors();

	//Books
	void save(Book book);

	Book findBook(Long id);

	List<Book> listBooks();

	int deleteBook(Long id);

	int updateBook(int pages, Long id);

	Long countBooks();
}
