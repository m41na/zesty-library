package com.practicaldime.quest.dao;

import java.util.List;

import com.practicaldime.quest.entity.Profile;

public interface ProfileRepository {

	void save(Profile author);
	
	Profile findOne(Long id);

	List<Profile> findAll();
	
	Long count();
}