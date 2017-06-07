
package ch.h2m.poi.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class ExampleService {

    private WebTarget webTarget;
    static final String ADDITION_URI = "http://date.jsontest.com";

    @Inject
    private long readTimeout;

    @Inject
    private long connectionTimeout;

    @PostConstruct
    public void init() {
        Client client = ClientBuilder.newClient();
        client.property("http.connection.timeout", this.connectionTimeout);
        System.out.println(connectionTimeout);
        System.out.println(readTimeout);
        client.property("http.receive.timeout", this.readTimeout);
        this.webTarget = client.target(ADDITION_URI);
    }

    public String callDateTime() {
        Response response = this.webTarget
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject jsonObject = response.readEntity(JsonObject.class);
        return jsonObject.getString("date");
    }
}
