
package ch.h2m.poi.api;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import ch.h2m.poi.model.PointOfInterest;
import ch.h2m.poi.service.PoIService;
import ch.h2m.poi.service.PoIStore;

@Path("poi")
@Produces(MediaType.APPLICATION_JSON)
public class PointOfInterestResource {

    @Inject
    PoIStore poIStore;

    @Inject
    PoIService poIService;

    @Context
    UriInfo uriInfo;

    @GET
    public List<PointOfInterest> getPointOfInterests() {
        final List<PointOfInterest> pois = poIStore.getPoIs();

        pois.forEach(u -> {
            final URI selfUri = uriInfo.getBaseUriBuilder().path(PointOfInterestResource.class).path(PointOfInterestResource.class, "getPointOfInterest").build(u.getId());
            u.getLinks().put("self", selfUri);
        });

        return pois;
    }


    @GET
    @Path("{id}")
    public PointOfInterest getPointOfInterest(@PathParam("id") long id) {
        final PointOfInterest poi = poIStore.getPoI(id);

        final URI selfUri = uriInfo.getBaseUriBuilder().path(PointOfInterestResource.class).path(PointOfInterestResource.class, "getPointOfInterest").build(poi.getId());
        poi.getLinks().put("self", selfUri);


        // check if business logic applies
        if (poIService.isVisitAllowed(poi)) {
            URI visit = uriInfo.getBaseUriBuilder().path(VisitResource.class).build();
            poi.getLinks().put("visit", visit);
        }

        return poi;
    }


}
