
package fr.ybonnel.breizhcampdemo;

import fr.ybonnel.simpleweb4j.test.SimpleWeb4jTest;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static fr.ybonnel.simpleweb4j.SimpleWeb4j.stop;
import static org.fest.assertions.Assertions.assertThat;

public class BreizhcampDemoWebTest extends SimpleWeb4jTest {

    @Before
    public void setup() {
        BreizhcampDemo.startServer(getPort(), false);
        GaletteResource.entities.clear();
        goTo("/");
        click("#linkAdmin");
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void should_not_have_hello() {
        assertThat(find("tbody tr")).isEmpty();
    }

    private void insertGalette(String nameOfGalette, Double priceOfGalate) {
        click("#addGalette");
        fill("#name").with(nameOfGalette);
        fill("#price").with(Double.toString(priceOfGalate));
        click("#submit");
    }

    @Test
    public void can_insert_galette() {
        insertGalette("Galette saucisse", 2.5);
        FluentList<FluentWebElement> trInTbody = find("tbody tr");
        assertThat(trInTbody).hasSize(1);
        FluentWebElement oneGalette = trInTbody.get(0);
        assertThat(oneGalette.findFirst("td").getText()).contains("Galette saucisse");
        assertThat(oneGalette.find("td", 1).getText()).contains("2.5");
    }

    @Test
    public void can_update_galette() {
        insertGalette("Galette saucisse", 2.5);
        click("a.icon-edit");
        clear("#name");
        fill("#name").with("Galette saucisse fromage");
        clear("#price");
        fill("#price").with("3.2");
        click("#submit");
        FluentList<FluentWebElement> trInTbody = find("tbody tr");
        assertThat(trInTbody).hasSize(1);
        FluentWebElement oneGalette = trInTbody.get(0);
        assertThat(oneGalette.findFirst("td").getText()).contains("Galette saucisse fromage");
        assertThat(oneGalette.find("td", 1).getText()).contains("3.2");
    }

    @Test
    public void can_delete_galette() {
        insertGalette("Galette saucisse", 2.5);
        click("a.icon-remove");
        click("#remove");
        assertThat(find("tbody tr")).isEmpty();
    }

}
