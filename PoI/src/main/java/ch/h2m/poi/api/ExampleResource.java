
package ch.h2m.poi.api;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ch.h2m.poi.service.ExampleService;
import ch.h2m.poi.service.ExampleStore;

@Path("example")
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @Inject
    ExampleService operations;

    @Inject
    ExampleStore exampleStore;

    @POST
    public Response save(JsonObject input, @Context UriInfo uriInfo) {
        String date = operations.callDateTime();

        URI uri = uriInfo.getAbsolutePathBuilder()
                .path(UUID.randomUUID().toString())
                .build();

        JsonObject stored = exampleStore.save(input, uri.toString(), date);

        return Response.created(uri).entity(stored).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAllExamples() {
        //TODO f.e. Paging, filtering, etc odata?

        Collection<JsonObject> all = exampleStore.getAll();

        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (JsonObject jsonObject : all) {
            builder.add(jsonObject);
        }

        return builder.build();
    }

    @GET
    @Path("{id}")
    public Response getExample(@PathParam("id") String id) {

        Optional<JsonObject> example = exampleStore.getById(id);

        return example
                .map(jsonObject -> Response
                        .ok(jsonObject)
                        .build())
                .orElseGet(() -> Response
                        .status(Response.Status.NOT_FOUND)
                        .build());
    }


}
