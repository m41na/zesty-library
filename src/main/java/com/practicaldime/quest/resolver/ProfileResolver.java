package com.practicaldime.quest.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.practicaldime.quest.entity.Profile;
import com.practicaldime.quest.entity.Question;

public class ProfileResolver implements GraphQLResolver<Question> {
	
//    private QuestService service;
//
//    public ProfileResolver(QuestService service) {
//        this.service = service;
//    }

    public Profile getProfile(Question que) {
        return que.getCreatedBy();
    }
}
