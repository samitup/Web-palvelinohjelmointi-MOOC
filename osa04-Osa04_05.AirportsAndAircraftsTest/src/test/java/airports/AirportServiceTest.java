package airports;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import static junit.framework.TestCase.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class AirportServiceTest {

    @Autowired
    private AirportService aService;
    @Autowired
    private AirportRepository aRepo;

    @Test
    public void createAirportTest() {
        aRepo.deleteAll();
        aService.create("testId", "testAirport");

        assertEquals("testAirport", aRepo.findByIdentifier("testId").getName());

    }

    @Test
    public void listAirports() {
        aRepo.deleteAll();
        aService.create("A", "asd");
        aService.create("B", "qwe");
        aService.create("C", "wer");
        assertEquals(3, aService.list().size());

    }

    @Test
    public void ifCreatedDontCreateAgain() {
        aRepo.deleteAll();
        aService.create("TestID", "TestName");
        aService.create("TestID", "TestName");
        aService.create("TestID", "NameTest");
        aService.create("abc", "dfg");

        assertEquals("Test failed.", 2, aService.list().size());
    }
}
