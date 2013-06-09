package fr.ybonnel.breizhcampdemo;

import fr.ybonnel.simpleweb4j.handlers.resource.RestResource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HelloResource extends RestResource<Hello> {

    protected static Map<Long, Hello> entities = new HashMap<>();

    public HelloResource(String route) {
        super(route, Hello.class);
    }

    @Override
    public Hello getById(String id) {
        return entities.get(Long.parseLong(id));
    }

    @Override
    public Collection<Hello> getAll() {
        return entities.values();
    }

    @Override
    public void update(String id, Hello resource) {
        resource.setId(Long.parseLong(id));
        entities.put(Long.parseLong(id), resource);
    }

    @Override
    public Hello create(Hello resource) {
        long maxId = 0;
        for (Hello hello : getAll()) {
            if (hello.getId() > maxId) {
                maxId = hello.getId();
            }
        }
        maxId++;
        resource.setId(maxId);
        entities.put(maxId, resource);
        return resource;
    }

    @Override
    public void delete(String id) {
        entities.remove(Long.parseLong(id));
    }

}