package brownieapi.apicontroller;

/**
 * Created by rich on 20/12/2017.
 */

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
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
public class getPointsAccountTest {


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
    public void testGetPointsAccess() throws Exception {

        pointsController = new PointsController();

        List<PointsAccount> points = Arrays.asList(
                new PointsAccount(1, "Daenerys Targaryen"),
                new PointsAccount(2, "John Snow"));

        System.out.println("This is the data: " + points.toString());

        when(repositoryOfPointsAccounts.findAll()).thenReturn(points);

        System.out.println("this is the mock: " + repositoryOfPointsAccounts.findAll());

        mockMvc.perform(get("/api/points"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].points", is(1)))
                .andExpect(jsonPath("$[0].name", is("Daenerys Targaryen")))
                .andExpect(jsonPath("$[1].points", is(2)))
                .andExpect(jsonPath("$[1].name", is("John Snow")));

    }

}
