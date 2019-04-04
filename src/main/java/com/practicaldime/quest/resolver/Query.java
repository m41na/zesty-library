package com.practicaldime.quest.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.practicaldime.quest.entity.Location;
import com.practicaldime.quest.entity.Profile;
import com.practicaldime.quest.entity.QueFilter;
import com.practicaldime.quest.entity.Question;
import com.practicaldime.quest.service.QuestService;

public class Query implements GraphQLQueryResolver {

	private QuestService service;

	public Query(QuestService service) {
		this.service = service;
	}

	public Iterable<Question> listQuestions(QueFilter filter) {
		return service.listQuestions(filter);
	}

	public Iterable<Profile> findAllProfiles() {
		return service.listProfiles();
	}

	public Iterable<Location> findAllLocations() {
		return service.listLocations();
	}
	
	public long countQuestions(Long authorId) {
		return service.countQuestions(authorId);
	}

	public long countProfiles() {
		return service.countProfiles();
	}

	public long countLocations() {
		return service.countLocations();
	}
}
