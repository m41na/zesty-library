package com.practicaldime.library.dao;

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

import com.practicaldime.library.config.DaoConfig;
import com.practicaldime.library.entity.Author;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@TestPropertySource("/test-jdbc.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AuthorRepositoryTest {

	@Resource
    private AuthorRepository authorDao;
     
    @Test
    public void testCreateFindAndCountOperations() {
        Author steve = new Author("Steve", "Mike");         
        authorDao.save(steve);
        assertEquals("Expecting 1", 1, steve.getId().longValue());
        
        Author mark = new Author("Mark", "Junis");         
        authorDao.save(mark);
        assertEquals("Expecting 2", 2, mark.getId().longValue());
        
        Author janie = new Author("Janie", "Watts");         
        authorDao.save(janie);
        assertEquals("Expecting 3", 3, janie.getId().longValue());
        
        List<Author> authors = authorDao.findAll();
        assertEquals("Expecting 3 authors", 3, authors.size());
        
        Author author = authorDao.findOne(2l);
        assertEquals("Expecting " + mark.getFirstName(), author.getFirstName(), "Mark");
        
        long count = authorDao.count();
        assertEquals("Expecting 3", 3, count);
    }
}
