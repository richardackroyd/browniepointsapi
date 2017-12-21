
package brownieapi.apicontroller;

/**
 * Created by rich on 21/12/2017.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTests {

    private MockMvc mockMvc;

    @InjectMocks
    private HelloController helloController;


    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(helloController)
                .build();

    }

    @Test
    public void testProvideGreetings() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Greetings from Spring Boot and Rich with profiles and tidied class!"));

    }

}
