package com.practicaldime.library.resolver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.library.entity.Book;
import com.practicaldime.library.service.LibraryService;

@Configuration
@ComponentScan({"com.practicaldime.library.config", "com.practicaldime.library.service"})
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
	public BookResolver authorResolver(LibraryService service) {
		return new BookResolver(service);
	}

	@Bean
	public Query query(LibraryService service) {
		return new Query(service);
	}

	@Bean
	public Mutation mutation(LibraryService service) {
		return new Mutation(service);
	}
}