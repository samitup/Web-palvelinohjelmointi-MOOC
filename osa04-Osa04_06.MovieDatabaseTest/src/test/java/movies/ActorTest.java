package movies;

import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorTest extends org.fluentlenium.adapter.junit.FluentTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void addAndDeleteActor() {
        //Go to page
        goTo("http://localhost:" + port + "/actors");
        //Check that "Uuno Turhapuro" doesn't exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        //Fill field with id value="name" with "Uuno Turhapuro"
        find("#name").fill().with("Uuno Turhapuro");
        //Search form with given id and send form
        find("form", withId().contains("postActorForm")).submit();
        //Check that "Uuno Turhapuro" has been added to page
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        //Click delete button associated with  "Uuno Turhapuro"
        find("#deleteActorForm").click().submit();
        //Check that "Uuno Turhapuro" doesnt exist on the page
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        

    }

}
