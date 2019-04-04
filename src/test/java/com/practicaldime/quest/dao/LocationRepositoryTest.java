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
import com.practicaldime.quest.dao.LocationRepository;
import com.practicaldime.quest.entity.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
@TestPropertySource("/test-jdbc.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class LocationRepositoryTest {

	@Resource
    private LocationRepository locationDao;
     
    @Test
    public void testCreateFindAndCountOperations() {
        Location chicago = new Location("chicago", "IL", "60451", "US");
        locationDao.save(chicago);
        assertEquals("Expecting 1", 1, chicago.getId().longValue());
        
        Location seattle = new Location("seattle", "WA", "98202", "US");         
        locationDao.save(seattle);
        assertEquals("Expecting 2", 2, seattle.getId().longValue());
        
        Location madison = new Location("madison", "WI", "53718", "US");        
        locationDao.save(madison);
        assertEquals("Expecting 3", 3, madison.getId().longValue());
        
        List<Location> authors = locationDao.findAll();
        assertEquals("Expecting 3 authors", 3, authors.size());
        
        Location location = locationDao.findOne(2l);
        assertEquals("Expecting " + seattle.getCity(), location.getCity(), "seattle");
        
        long count = locationDao.count();
        assertEquals("Expecting 3", 3, count);
    }
}
