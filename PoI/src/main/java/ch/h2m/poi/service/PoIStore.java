
package ch.h2m.poi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import ch.h2m.poi.model.PointOfInterest;

@ApplicationScoped
public class PoIStore {

    private ConcurrentHashMap<Long, PointOfInterest> poIStore;

    @PostConstruct
    public void init() {
        this.poIStore = new ConcurrentHashMap<>();

        initTestData();
    }

    public PointOfInterest save(PointOfInterest pointOfInterest) {
        poIStore.put(pointOfInterest.getId(), pointOfInterest);
        return pointOfInterest;
    }

    public List<PointOfInterest> getPoIs() {
        return new ArrayList<>(poIStore.values());
    }

    public PointOfInterest getPoI(long id) {
        return poIStore.get(id);
    }

    private void initTestData() {
        PointOfInterest p1 = new PointOfInterest();
        p1.setId(1);
        p1.setName("test 1");


        PointOfInterest p2 = new PointOfInterest();
        p2.setId(2);
        p2.setName("test 2");

        save(p1);
        save(p2);
    }


}
