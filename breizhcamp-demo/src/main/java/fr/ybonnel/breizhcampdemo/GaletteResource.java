package fr.ybonnel.breizhcampdemo;

import fr.ybonnel.simpleweb4j.handlers.resource.RestResource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GaletteResource extends RestResource<Galette> {

    protected static Map<Long, Galette> entities = new HashMap<>();

    public GaletteResource(String route) {
        super(route, Galette.class);
    }

    @Override
    public Galette getById(String id) {
        return entities.get(Long.parseLong(id));
    }

    @Override
    public Collection<Galette> getAll() {
        return entities.values();
    }

    @Override
    public void update(String id, Galette resource) {
        resource.setId(Long.parseLong(id));
        entities.put(Long.parseLong(id), resource);
    }

    @Override
    public Galette create(Galette resource) {
        long maxId = 0;
        for (Galette galette : getAll()) {
            if (galette.getId() > maxId) {
                maxId = galette.getId();
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