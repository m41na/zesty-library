package com.practicaldime.library.service;

import com.coxautodev.graphql.tools.SchemaParser;
import com.practicaldime.library.resolver.BookResolver;
import com.practicaldime.library.resolver.LibraryContext;
import com.practicaldime.library.resolver.Mutation;
import com.practicaldime.library.resolver.Query;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;

public class LibraryEndpoint {

	private static LibraryContext ctx;

	public static SimpleGraphQLServlet create() {
		return SimpleGraphQLServlet.create(buildSchema());
	}
	
	public static GraphQLSchema buildSchema() {
		ctx = new LibraryContext();
		ctx.init();
		//get service bean
		Query query = ctx.getBean("", Query.class);
		Mutation mutation = ctx.getBean("", Mutation.class);
		BookResolver resolver = ctx.getBean("", BookResolver.class);
		
		return SchemaParser.newParser()
        .files("book.graphql", "author.graphql")
        .resolvers(query, mutation, resolver)
        .build()
        .makeExecutableSchema();
	}
}
