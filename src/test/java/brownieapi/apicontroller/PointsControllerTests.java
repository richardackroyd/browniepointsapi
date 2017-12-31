
package brownieapi.apicontroller;

/**
 * Created by rich on 20/12/2017.
 */

import brownieapi.model.PointsAccount;
import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.AddPointsToAccount;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PointsControllerTests {

    private MockMvc mockMvc;

    @Mock
    private PointsCollectorRepository repositoryOfPointsAccounts;

    @InjectMocks
    private PointsController pointsController;


    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(pointsController)
                .build();

    }

    @Test
    public void testGetPointsAccounts() throws Exception {

        List<PointsAccount> points = Arrays.asList(
                new PointsAccount(1, "Daenerys Targaryen"),
                new PointsAccount(2, "John Snow"));

        when(repositoryOfPointsAccounts.findAll()).thenReturn(points);

        mockMvc.perform(get("/api/points"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].points", is(1)))
                .andExpect(jsonPath("$[0].name", is("Daenerys Targaryen")))
                .andExpect(jsonPath("$[1].points", is(2)))
                .andExpect(jsonPath("$[1].name", is("John Snow")));

    }

    @Test
    public void testGetPointsAccount() throws Exception {

        PointsAccount pointsAccount = new PointsAccount(5, "Bryan Clough");
        Long id = Integer.toUnsignedLong(0);

        when(repositoryOfPointsAccounts.findOne(Integer.toUnsignedLong(1))).thenReturn(pointsAccount);

        mockMvc.perform(get("/api/points/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("points", is(5)))
                .andExpect(jsonPath("name", is("Bryan Clough")));

    }

    @Test
    public void testGetPointsAccountNull() throws Exception {

        PointsAccount pointsAccount = null;
        Long id = Integer.toUnsignedLong(0);

        when(repositoryOfPointsAccounts.findOne(Integer.toUnsignedLong(1))).thenReturn(pointsAccount);

        mockMvc.perform(get("/api/points/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("points", is(0)))
                .andExpect(jsonPath("name", is("No data")));

    }

    @Test
    public void testCreatePointsAccount() throws Exception {

        PointsAccount pointsAccount = new PointsAccount(1234, "Bryan Clough");

        when(repositoryOfPointsAccounts.save(pointsAccount)).thenReturn(pointsAccount);

        mockMvc.perform(post("/api/addEntry")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"Bryan Clough\", \"points\":1234}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddPointsAccount() throws Exception {

        AddPointsToAccount pointsAccountChangePoints =
                new AddPointsToAccount(1);
        PointsAccount pointsAccount = new PointsAccount(1, "richwithprofiles");
        pointsAccount.setID(new Long(1));
        Long idToChange = new Long(1);

        when(repositoryOfPointsAccounts.findOne(idToChange)).thenReturn(pointsAccount);

        mockMvc.perform(put("/api/addPoint/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"changeByPoints\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    public void testRemovePointsAccount() throws Exception {

        AddPointsToAccount pointsAccountChangePoints =
                new AddPointsToAccount(1);
        PointsAccount pointsAccount = new PointsAccount(1, "richwithprofiles");
        pointsAccount.setID(new Long(1));
        Long idToChange = new Long(1);

        when(repositoryOfPointsAccounts.findOne(idToChange)).thenReturn(pointsAccount);

        mockMvc.perform(put("/api/removePoint/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"changeByPoints\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

}
