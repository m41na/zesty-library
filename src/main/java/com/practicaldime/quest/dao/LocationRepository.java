package com.practicaldime.quest.dao;

import java.util.List;

import com.practicaldime.quest.entity.Location;

public interface LocationRepository {

	void save(Location loc);
	
	Location findOne(Long id);

	List<Location> findAll();
	
	Long count();
}