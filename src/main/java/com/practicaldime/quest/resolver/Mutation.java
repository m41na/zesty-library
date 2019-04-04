package com.practicaldime.quest.resolver;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.practicaldime.quest.entity.Answer;
import com.practicaldime.quest.entity.Profile;
import com.practicaldime.quest.entity.Question;
import com.practicaldime.quest.service.QuestService;

public class Mutation implements GraphQLMutationResolver {

	private QuestService service;

	public Mutation(QuestService service) {
		this.service = service;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Profile newProfile(String email, String firstName, String lastName) {
		Profile author = new Profile();
		author.setEmail(email);
		author.setFirstName(firstName);
		author.setLastName(lastName);
		service.save(author);

		return author;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Question newQuestion(String content, String answer, Long authorId) {
		Question que = new Question();
		Profile author = new Profile();
		author.setId(authorId);
		que.setCreatedBy(author);
		que.setContent(content);
		que.setAnswer(new Answer(answer));
		service.save(que);

		return que;
	}

	public int deleteQuestion(Long id) {
		return service.deleteQuestion(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Question updateQuestion(Question input) {
		Question updated = service.updateQuestion(input);
        return updated;
    }
}
