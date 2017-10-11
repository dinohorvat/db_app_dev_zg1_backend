package database.application.development.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    CustomerDao customerDao;
    @Test
    public void loadContext(){
        assertEquals(true, true);
    }
}
