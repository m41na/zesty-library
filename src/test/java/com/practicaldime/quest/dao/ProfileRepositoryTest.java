package com.practicaldime.quest.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.practicaldime.quest.config.DaoConfig;
import com.practicaldime.quest.dao.ProfileRepository;
import com.practicaldime.quest.entity.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@TestPropertySource("/test-jdbc.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ProfileRepositoryTest {

	@Resource
    private ProfileRepository profileDao;
     
    @Test
    public void testCreateFindAndCountOperations() {
        Profile steve = new Profile("Steve", "Mike", "steve.main@email.com");         
        profileDao.save(steve);
        assertEquals("Expecting 1", 1, steve.getId().longValue());
        
        Profile mark = new Profile("Mark", "Junis", "mark.main@email.com");         
        profileDao.save(mark);
        assertEquals("Expecting 2", 2, mark.getId().longValue());
        
        Profile janie = new Profile("Janie", "Watts", "janie.main@email.com");         
        profileDao.save(janie);
        assertEquals("Expecting 3", 3, janie.getId().longValue());
        
        List<Profile> authors = profileDao.findAll();
        assertEquals("Expecting 3 authors", 3, authors.size());
        
        Profile author = profileDao.findOne(2l);
        assertEquals("Expecting " + mark.getFirstName(), author.getFirstName(), "Junis");
        
        long count = profileDao.count();
        assertEquals("Expecting 3", 3, count);
    }
}
