
package brownieapi.model;

/**
 * Created by rich on 21/12/2017.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PointsAccountTests {

    @InjectMocks
    private PointsAccount pointsAccount;


    @Before
    public void init(){

        pointsAccount = new PointsAccount(5, "Rich");

    }


    @Test
    public void testGetName() throws Exception {

        Assert.assertEquals("Rich", pointsAccount.getName());

    }

    @Test
    public void testGetPoints() throws Exception {

        Assert.assertEquals(5, pointsAccount.getPoints());

    }

    //ID not set until saved to persistence (it is an autoincrement) so in POJO creation will be null.

    @Test
    public void testGetID() throws Exception {

        Assert.assertEquals(null, pointsAccount.getID());

    }

    @Test
    public void testSetName() throws Exception {

        pointsAccount.setName("James");

        Assert.assertEquals("James", pointsAccount.getName());

    }

    @Test
    public void testSetPoints() throws Exception {

        pointsAccount.setPoints(10);

        Assert.assertEquals(10, pointsAccount.getPoints());

    }

    @Test
    public void testSetID() throws Exception {

        pointsAccount.setID(new Long(8));

        Assert.assertEquals(new Long(8), pointsAccount.getID());

    }

}
