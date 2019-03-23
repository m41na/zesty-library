package com.practicaldime.library.resolver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.library.dao.AuthorRepository;
import com.practicaldime.library.dao.BookRepository;
import com.practicaldime.library.entity.Book;

@Configuration
@ComponentScan({"com.practicaldime.library.config"})
public class LibraryContext implements GraphQLResolver<Book> {
	
private AnnotationConfigApplicationContext ctx;
	
	public void init() {
		ctx = new AnnotationConfigApplicationContext(LibraryContext.class);
	}
	
	@SuppressWarnings("unchecked")
	public <T>T getBean(String name, Class<T> type){
		if(type == null) {
			return (T) ctx.getBean(name);
		}
		else if(name == null || name.trim().length() == 0) {
			return ctx.getBean(type);
		}
		else {
			return ctx.getBean(name, type);
		}
	}
	
	@Bean
	public BookResolver authorResolver(AuthorRepository authorRepository) {
		return new BookResolver(authorRepository);
	}

	@Bean
	public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Query(authorRepository, bookRepository);
	}

	@Bean
	public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
		return new Mutation(authorRepository, bookRepository);
	}
}