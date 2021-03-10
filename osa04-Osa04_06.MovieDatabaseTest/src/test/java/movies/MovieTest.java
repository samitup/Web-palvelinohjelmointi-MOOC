package movies;

import static org.fluentlenium.core.filter.FilterConstructor.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieTest extends org.fluentlenium.adapter.junit.FluentTest {

    @LocalServerPort
    private Integer port;

    @Test
    public void doSomething() {
        //Go to movie page
        goTo("http://localhost:" + port + "/movies");
        //Check that text "Uuno Epsanjassa" doesnt exist
        assertFalse(pageSource().contains("Uuno Epsanjassa"));
        //Check that text "Uuno Turhapuro" doesnt exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        //Fill field with id value="name" with "Uuno Turhapuro"
        find("#name").fill().with("Uuno Epsanjassa");
        //Search field with id "lengthInMinutes" and fill with value="92"
        find("#lengthInMinutes").fill().with("92");
        //Send form
        find("form", withId().contains("postMovieForm")).submit();
        //Check that "Uuno Epsanjassa" has been added to page
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        //Check that "Uuno Turhapuro" doesnt exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        //Go to actors page
        goTo("http://localhost:" + port + "/actors");
        //Check that "Uuno Turhapuro" doesnt exist
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        //Search field with id "name" and set text "Uuno Turhapuro"
        find("#name").fill().with("Uuno Turhapuro");
        //Send form
        find("form", withId().contains("postActorForm")).submit();
        //Check that text "Uuno Turhapuro" exists
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        //Find and click link with text "Uuno Turhapuro"
        find(By.linkText("Uuno Turhapuro")).click();
        //Find button with id value="add-to-movie" and click it
        find("#add-to-movie").click();
        // Go to movie page
        goTo("http://localhost:" + port + "/movies");
        //Check that text "Uuno Epsanjassa"  exists
        assertTrue(pageSource().contains("Uuno Epsanjassa"));
        //Check that text "Uuno Turhapuro"  exists
        assertTrue(pageSource().contains("Uuno Turhapuro"));

    }

}
