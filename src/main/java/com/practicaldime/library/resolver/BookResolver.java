package com.practicaldime.library.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.library.dao.AuthorRepository;
import com.practicaldime.library.entity.Author;
import com.practicaldime.library.entity.Book;

public class BookResolver implements GraphQLResolver<Book> {
	
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}