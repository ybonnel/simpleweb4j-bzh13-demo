
package fr.ybonnel.breizhcampdemo;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.ybonnel.simpleweb4j.test.SimpleWeb4jTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static fr.ybonnel.simpleweb4j.SimpleWeb4j.stop;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BreizhcampDemoTest extends SimpleWeb4jTest {

    @Before
    public void setup() {
        BreizhcampDemo.startServer(getPort(), false);
        GaletteResource.entities.clear();
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void should_return_no_galette() {
        List<Galette> galettes = new Gson().fromJson(HttpRequest.get(defaultUrl() + "/galette").body(), new TypeToken<List<Class>>(){}.getType());
        assertTrue(galettes.isEmpty());
    }

    public Long insertGalette() {
        Galette galette = new Galette();
        galette.setName("Galette Saucisse");
        galette.setPrice(2.5);
        Gson gson = new Gson();
        assertEquals(201, HttpRequest.post(defaultUrl() + "/galette").send(gson.toJson(galette)).code());

        List<Galette> galettes = gson.fromJson(HttpRequest.get(defaultUrl() + "/galette").body(), new TypeToken<List<Galette>>(){}.getType());
        assertEquals(1, galettes.size());
        assertEquals("Galette Saucisse", galettes.get(0).getName());
        assertEquals(2.5, galettes.get(0).getPrice(), 0.001);
        return galettes.get(0).getId();
    }

    @Test
    public void should_create_and_get_by_id() {
        long id = insertGalette();

        Galette newGalette = new Gson().fromJson(HttpRequest.get(defaultUrl() + "/galette/" + id).body(), Galette.class);
        assertNotNull(newGalette);
        assertEquals(id, newGalette.getId().longValue());
        assertEquals("Galette Saucisse", newGalette.getName());
        assertEquals(2.5, newGalette.getPrice(), 0.001);
    }

    @Test
    public void should_update() {
        long id = insertGalette();
        Gson gson = new Gson();

        Galette newGalette = new Galette();
        newGalette.setName("Galette Saucisse fromage");

        assertEquals(204, HttpRequest.put(defaultUrl() + "/galette/" + id).send(gson.toJson(newGalette)).code());

        List<Galette> galettes = gson.fromJson(HttpRequest.get(defaultUrl() + "/galette").body(), new TypeToken<List<Galette>>(){}.getType());
        assertEquals(1, galettes.size());
        assertEquals("Galette Saucisse fromage", galettes.get(0).getName());
    }

    @Test
    public void should_delete() {
        long id = insertGalette();
        Gson gson = new Gson();

        assertEquals(204, HttpRequest.delete(defaultUrl() + "/galette/" + id).code());
        List<Galette> galettes = gson.fromJson(HttpRequest.get(defaultUrl() + "/galette").body(), new TypeToken<List<Galette>>(){}.getType());
        assertTrue(galettes.isEmpty());
    }

}
