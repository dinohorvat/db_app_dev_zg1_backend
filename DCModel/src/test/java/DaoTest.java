import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by HrvojeGrgic on 11/10/2017.
 */
@SpringBootTest(classes = {SpringApplication.class})
public class DaoTest {

    @Test
    public void loadContext(){
        assertEquals(true, true);
    }
}
