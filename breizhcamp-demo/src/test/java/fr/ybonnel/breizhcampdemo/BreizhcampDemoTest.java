
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
        HelloResource.entities.clear();
    }

    @After
    public void tearDown() {
        stop();
    }

    @Test
    public void should_return_no_hello() {
        List<Hello> hellos = new Gson().fromJson(HttpRequest.get(defaultUrl() + "/hello").body(), new TypeToken<List<Class>>(){}.getType());
        assertTrue(hellos.isEmpty());
    }

    public Long insertHello() {
        Hello hello = new Hello();
        hello.setName("name");
        Gson gson = new Gson();
        assertEquals(201, HttpRequest.post(defaultUrl() + "/hello").send(gson.toJson(hello)).code());

        List<Hello> hellos = gson.fromJson(HttpRequest.get(defaultUrl() + "/hello").body(), new TypeToken<List<Hello>>(){}.getType());
        assertEquals(1, hellos.size());
        assertEquals("name", hellos.get(0).getName());
        return hellos.get(0).getId();
    }

    @Test
    public void should_create_and_get_by_id() {
        long id = insertHello();

        Hello newHello = new Gson().fromJson(HttpRequest.get(defaultUrl() + "/hello/" + id).body(), Hello.class);
        assertNotNull(newHello);
        assertEquals(id, newHello.getId().longValue());
        assertEquals("name", newHello.getName());
    }

    @Test
    public void should_update() {
        long id = insertHello();
        Gson gson = new Gson();

        Hello newHello = new Hello();
        newHello.setName("newName");

        assertEquals(204, HttpRequest.put(defaultUrl() + "/hello/" + id).send(gson.toJson(newHello)).code());

        List<Hello> hellos = gson.fromJson(HttpRequest.get(defaultUrl() + "/hello").body(), new TypeToken<List<Hello>>(){}.getType());
        assertEquals(1, hellos.size());
        assertEquals("newName", hellos.get(0).getName());
    }

    @Test
    public void should_delete() {
        long id = insertHello();
        Gson gson = new Gson();

        assertEquals(204, HttpRequest.delete(defaultUrl() + "/hello/" + id).code());
        List<Hello> hellos = gson.fromJson(HttpRequest.get(defaultUrl() + "/hello").body(), new TypeToken<List<Hello>>(){}.getType());
        assertTrue(hellos.isEmpty());
    }

}
