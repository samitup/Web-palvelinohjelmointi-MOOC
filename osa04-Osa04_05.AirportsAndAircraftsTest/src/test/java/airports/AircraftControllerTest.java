package airports;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AircraftRepository acRepo;

    @Test
    public void modelHasAttributesAndStatusOK() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk()).andExpect(model().attributeExists("aircrafts", "airports"));
    }

    @Test
    public void testNameParameterAndPOSTRequest() throws Exception {
        acRepo.deleteAll();
        mockMvc.perform(post("/aircrafts")
                .param("name", "HA-LOL")).andExpect(status().isFound());
        assertEquals("No match found from database!", "HA-LOL", acRepo.findAll().listIterator().next().getName());
    }

    @Test
    public void testIfCreated() throws Exception {
        acRepo.deleteAll();
        mockMvc.perform(post("/aircrafts")
                .param("name", "XP-55")).andExpect(status().isFound());

        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attribute("aircrafts", acRepo.findAll()));

    }

}
