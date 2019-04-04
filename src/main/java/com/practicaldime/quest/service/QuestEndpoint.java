package com.practicaldime.quest.service;

import com.coxautodev.graphql.tools.SchemaParser;
import com.practicaldime.quest.entity.SimpleDateScalar;
import com.practicaldime.quest.resolver.AnswerResolver;
import com.practicaldime.quest.resolver.Mutation;
import com.practicaldime.quest.resolver.ProfileResolver;
import com.practicaldime.quest.resolver.Query;
import com.practicaldime.quest.resolver.QuestContext;

import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLConfiguration;
import graphql.servlet.GraphQLHttpServlet;

public class QuestEndpoint extends GraphQLHttpServlet{

	private static final long serialVersionUID = 1L;
	private static QuestContext ctx;

	@Override
	protected GraphQLConfiguration getConfiguration() {
		return GraphQLConfiguration.with(buildSchema()).build();
	}
	
	public static GraphQLSchema buildSchema() {
		ctx = new QuestContext();
		ctx.init();
		//get service bean
		Query query = ctx.getBean("", Query.class);
		Mutation mutation = ctx.getBean("", Mutation.class);
		ProfileResolver profileRes = ctx.getBean("", ProfileResolver.class);
		AnswerResolver answerRes = ctx.getBean("", AnswerResolver.class);
		
		return SchemaParser.newParser()
        .files("schema.graphql")
        .resolvers(query, mutation, profileRes, answerRes)
		.scalars(new SimpleDateScalar())
        .build()
        .makeExecutableSchema();
	}
}
