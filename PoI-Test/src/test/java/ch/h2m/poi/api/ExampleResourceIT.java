package ch.h2m.poi.api;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author h2mch
 */
public class ExampleResourceIT {

    private WebTarget webTarget;
    private static final String EXAMPLE_URI = "http://localhost:8080/h2mpoi/resources/example";

    @Before
    public void init() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target(EXAMPLE_URI);
    }

    @Test
    public void createExample() {
        int number = 123;
        JsonObject input = Json.createObjectBuilder().
                add("string", "value1").
                add("number", number).
                build();
        Response postResponse = this.webTarget.
                request(MediaType.APPLICATION_JSON).
                post(json(input));

        assertThat(postResponse.getStatus(), is(201));

        JsonObject jsonResult = postResponse.readEntity(JsonObject.class);
        String href = jsonResult.getJsonObject("links").getString("href");
        assertThat(href, startsWith(EXAMPLE_URI));


        Response getResponse = ClientBuilder.newClient()
                .target(href)
                .request(MediaType.APPLICATION_JSON)
                .get();

        jsonResult = getResponse.readEntity(JsonObject.class);
        int returnNumber = jsonResult.getJsonNumber("number").intValue();

        assertThat(returnNumber, is(number));
    }

}
