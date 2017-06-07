
package ch.h2m.poi.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("visit")
@Produces(MediaType.APPLICATION_JSON)
public class VisitResource {

    @Context
    UriInfo uriInfo;

    @GET
    public Response getPointOfInterests() {
        return Response.noContent().build();
    }

    @GET
    @Path("{id}")
    public Response getPointOfInterest(@PathParam("id") long id) {
        return Response.noContent().build();
    }


}
