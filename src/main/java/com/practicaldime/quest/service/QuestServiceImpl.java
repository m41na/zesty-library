package com.practicaldime.quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.practicaldime.quest.dao.LocationRepository;
import com.practicaldime.quest.dao.ProfileRepository;
import com.practicaldime.quest.dao.QuestionRepository;
import com.practicaldime.quest.entity.Location;
import com.practicaldime.quest.entity.Profile;
import com.practicaldime.quest.entity.QueFilter;
import com.practicaldime.quest.entity.Question;

@Service
@Transactional
public class QuestServiceImpl implements QuestService {

	@Autowired
	private QuestionRepository questionDao;
	@Autowired
	private ProfileRepository profileDao;
	@Autowired
	private LocationRepository locationRepo;
	
	@Override
	public void save(Profile profile) {
		profileDao.save(profile);
	}

	@Override
	public List<Profile> listProfiles() {
		return profileDao.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Question que) {
		Profile profile = profileDao.findOne(que.getCreatedBy().getId());
		que.setCreatedBy(profile);
		questionDao.save(que);
	}

	@Override
	public List<Question> listQuestions(QueFilter filter) {
		return questionDao.findAll(filter);
	}

	@Override
	public Profile findProfile(Long id) {
		return profileDao.findOne(id);
	}

	@Override
	public Long countProfiles() {
		return profileDao.count();
	}

	@Override
	public void save(Location loc) {
		locationRepo.save(loc);
	}

	@Override
	public Location findLocation(Long id) {
		return locationRepo.findOne(id);
	}

	@Override
	public List<Location> listLocations() {
		return locationRepo.findAll();
	}

	@Override
	public Long countLocations() {
		return locationRepo.count();
	}

	@Override
	public Question findQuestion(Long id) {
		return questionDao.findOne(id);
	}

	@Override
	public int deleteQuestion(Long id) {
		return questionDao.delete(id);
	}

	@Override
	public Question updateQuestion(Question que) {
		return questionDao.update(que);
	}

	@Override
	public Long countQuestions(Long authorId) {
		return questionDao.count(authorId);
	}
}
