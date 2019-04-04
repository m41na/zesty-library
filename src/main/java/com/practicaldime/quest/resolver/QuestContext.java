package com.practicaldime.quest.resolver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.quest.entity.Question;
import com.practicaldime.quest.service.QuestService;

@Configuration
@ComponentScan({"com.practicaldime.quest.config", "com.practicaldime.quest.service"})
public class QuestContext implements GraphQLResolver<Question> {
	
private AnnotationConfigApplicationContext ctx;
	
	public void init() {
		ctx = new AnnotationConfigApplicationContext(QuestContext.class);
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
	public ProfileResolver authorResolver() {
		return new ProfileResolver();
	}
	
	@Bean
	public AnswerResolver answerResolver() {
		return new AnswerResolver();
	}

	@Bean
	public Query query(QuestService service) {
		return new Query(service);
	}

	@Bean
	public Mutation mutation(QuestService service) {
		return new Mutation(service);
	}
}