package com.practicaldime.library.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;
import com.practicaldime.library.service.LibraryService;

public class BookResolver implements GraphQLResolver<Book> {
	
    private LibraryService service;

    public BookResolver(LibraryService service) {
        this.service = service;
    }

    public Author getAuthor(Book book) {
        return service.findAuthor(book.getAuthor().getId());
    }
}
