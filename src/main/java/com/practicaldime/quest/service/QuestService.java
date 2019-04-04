package com.practicaldime.quest.service;

import java.util.List;

import com.practicaldime.quest.entity.Location;
import com.practicaldime.quest.entity.Profile;
import com.practicaldime.quest.entity.QueFilter;
import com.practicaldime.quest.entity.Question;

public interface QuestService {

	// Profile
	void save(Profile profile);

	Profile findProfile(Long id);

	List<Profile> listProfiles();

	Long countProfiles();

	// Location
	void save(Location loc);

	Location findLocation(Long id);

	List<Location> listLocations();

	Long countLocations();

	// Question
	void save(Question book);

	Question findQuestion(Long id);

	List<Question> listQuestions(QueFilter filter);

	int deleteQuestion(Long id);

	Question updateQuestion(Question que);

	Long countQuestions(Long authorId);
}
