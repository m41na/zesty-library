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
import com.practicaldime.library.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@TestPropertySource("/test-jdbc.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class BookRepositoryTest {

	@Resource
    private BookRepository bookDao;
     
    @Test
    public void testCreateFindAndCountOperations() {
    	Author steve = new Author("Steve", "Main");            
        Book dreams = new Book("Dreams", "12345", 40, steve);         
        bookDao.save(dreams);
        assertEquals("Expecting 1", 1, dreams.getId().longValue());
        
        Book places = new Book("Places", "23456", 50, steve);         
        bookDao.save(places);
        assertEquals("Expecting 3", 3, places.getId().longValue());
        
        Author janie = new Author("Janie", "Watts");    
        Book recipes = new Book("Delicacies", "34567", 40, janie);         
        bookDao.save(recipes);
        assertEquals("Expecting 4", 4, recipes.getId().longValue());
        
        Book fitness = new Book("Keep fit", "45678", 60, steve);         
        bookDao.save(fitness);
        assertEquals("Expecting 6", 6, fitness.getId().longValue());
        
        List<Book> books = bookDao.findAll();
        assertEquals("Expecting 4 books", 4, books.size());
        
        Book book = bookDao.findOne(3l);
        assertEquals("Expecting " + places.getTitle(), book.getTitle(), "Places");
        
        long count = bookDao.count();
        assertEquals("Expecting 4", 4, count);
        
        int updated = bookDao.update(78, 3l);
        assertEquals("Expecting 1", 1, updated);
        
        int deleted = bookDao.delete(3l);
        assertEquals("Expecting 1", 1, deleted);
        
        long newCount = bookDao.count();
        assertEquals("Expecting 3", 3, newCount);
    }
}
