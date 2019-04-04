package com.practicaldime.quest.dao;

import java.util.List;

import com.practicaldime.quest.entity.QueFilter;
import com.practicaldime.quest.entity.Question;

public interface QuestionRepository {
	
	void save(Question que);
	
	Question findOne(Long id);

	List<Question> findAll(QueFilter filter);
	
	int delete(Long id);
	
	Question update(Question que);
	
	Long count(Long authordId);
}