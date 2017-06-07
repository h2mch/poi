
package ch.h2m.poi.service;

import javax.ejb.Stateless;

import ch.h2m.poi.model.PointOfInterest;

@Stateless
public class PoIService {

    public boolean isVisitAllowed(PointOfInterest pointOfInterest) {
        return true;
    }
}
