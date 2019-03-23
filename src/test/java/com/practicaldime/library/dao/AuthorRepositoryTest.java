package com.practicaldime.library.dao;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.practicaldime.library.config.TestDaoConfig;
import com.practicaldime.library.entity.Author;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDaoConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class AuthorRepositoryTest {

	@Resource
    private AuthorRepository authorDao;
     
    @Test
    public void testAdd() {
        Author author = new Author("Steve", "Mike");
         
        authorDao.save(author);
        assertEquals("Expecting 1", 1, author.getId().longValue());
    }
}
