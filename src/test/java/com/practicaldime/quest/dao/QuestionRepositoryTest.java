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
import com.practicaldime.quest.dao.QuestionRepository;
import com.practicaldime.quest.entity.Profile;
import com.practicaldime.quest.entity.QueFilter;
import com.practicaldime.quest.entity.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@TestPropertySource("/test-jdbc.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class QuestionRepositoryTest {

	@Resource
    private QuestionRepository queDao;
     
    @Test
    public void testCreateFindAndCountOperations() {
    	Profile steve = new Profile("Steve", "Main", "steve.main@email.com");            
        Question one_one = new Question("1 + 1", "2");  
        one_one.setCreatedBy(steve);
        queDao.save(one_one);
        assertEquals("Expecting 2", "2", one_one.getAnswer().getContent());
        
        Question two_two = new Question("2 + 2", "4");   
        two_two.setCreatedBy(steve);
        queDao.save(two_two);
        assertEquals("Expecting 4", "4", two_two.getAnswer().getContent());
        
        Profile janie = new Profile("Janie", "Watts", "janie.main@email.com");    
        Question one_by_one = new Question("1 * 1", "1");
        one_by_one.setCreatedBy(janie);
        queDao.save(one_by_one);
        assertEquals("Expecting 1", "1", one_by_one.getAnswer().getContent());
        
        Question two_by_two = new Question("2 * 2", "4");
        two_by_two.setCreatedBy(janie);
        queDao.save(two_by_two);
        assertEquals("Expecting 4", "4", two_by_two.getAnswer().getContent());
        
        List<Question> steveQues = queDao.findAll(new QueFilter(steve.getId(), null, null, null, null, null));
        assertEquals("Expecting 2 books", 2, steveQues.size());
        
        Question steveQu2 = queDao.findOne(two_two.getId());
        assertEquals("Expecting " + two_two.getContent(), steveQu2.getContent(), two_two.getContent());
        
        steveQu2.getAnswer().setReason("Simple as pie");
        Question updated = queDao.update(steveQu2);
        assertEquals("Expecting 'Simple as pie'", updated.getAnswer().getReason(), "Simple as pie");
        
        int deleted = queDao.delete(two_two.getId());
        assertEquals("Expecting 1", 1, deleted);
        
        long newCount = queDao.count(steve.getId());
        assertEquals("Expecting 1", 1, newCount);
    }
}
