
package brownieapi;

/**
 * Created by rich on 21/12/2017.
 */

import brownieapi.Application;
import brownieapi.model.PointsAccount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

public class ApplicationTests {

    @Test
    public void test()
    {
        Application.main(new String[]{
        });
    }

}
