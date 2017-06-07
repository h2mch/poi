
package ch.h2m.poi.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("status")
public class StatusResource {

    @GET
    @Path("ping")
    public Response ping() {
        return Response.ok("pong").build();
    }

}
