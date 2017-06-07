
package ch.h2m.poi.service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

@ApplicationScoped
public class ExampleStore {

    private ConcurrentHashMap<String, JsonObject> store;

    @PostConstruct
    public void init() {
        store = new ConcurrentHashMap<>();
    }

    public JsonObject save(JsonObject input, String href, String date) {

        String id = href.substring(href.lastIndexOf('/') +1);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", id);
        builder.add("date", date);

        JsonObject jsonLink = Json.createObjectBuilder()
                .add("rel", Json.createArrayBuilder().add("self").build())
                .add("href", href)
                .build();
        builder.add("links", jsonLink);

        for (Map.Entry<String, JsonValue> entry : input.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        JsonObject storedObject = builder.build();
        store.put(id, storedObject);

        return storedObject;
    }

    public Collection<JsonObject> getAll() {
        return store.values();
    }

    public Optional<JsonObject> getById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
