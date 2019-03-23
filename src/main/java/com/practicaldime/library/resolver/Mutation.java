package com.practicaldime.library.resolver;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;
import com.practicaldime.library.exception.BookNotFoundException;
import com.practicaldime.library.service.LibraryService;

public class Mutation implements GraphQLMutationResolver {

	private LibraryService service;

	public Mutation(LibraryService service) {
		this.service = service;
	}

	public Author newAuthor(String firstName, String lastName) {
		Author author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);

		service.save(author);

		return author;
	}

	public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
		Book book = new Book();
		book.setAuthor(new Author(authorId));
		book.setTitle(title);
		book.setIsbn(isbn);
		book.setPageCount(pageCount != null ? pageCount : 0);

		service.save(book);

		return book;
	}

	public boolean deleteBook(Long id) {
		service.deleteBook(id);
		return true;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Book updateBookPageCount(Integer pageCount, Long id) {
		Book book = service.findBook(id);
        if(book == null) {
            throw new BookNotFoundException("The book to be updated was not found", id);
        }
        book.setPageCount(pageCount);

        service.save(book);

        return book;
    }
}
