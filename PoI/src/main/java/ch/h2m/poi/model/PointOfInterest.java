package ch.h2m.poi.model;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PointOfInterest {


    @XmlTransient
    private long id;
    private String name;

    @XmlElement(name = "_links")
    private Map<String, URI> links = new HashMap<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, URI> getLinks() {
        return links;
    }

    public void setLinks(Map<String, URI> links) {
        this.links = links;
    }
}
