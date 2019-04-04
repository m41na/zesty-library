package com.practicaldime.quest.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.quest.entity.Answer;
import com.practicaldime.quest.entity.Question;

public class AnswerResolver implements GraphQLResolver<Question> {
	
//    private QuestService service;
//
//    public AnswerResolver(QuestService service) {
//        this.service = service;
//    }

    public Answer getAnswer(Question que) {
        return  que.getAnswer();
    }
}
