package com.anefyodov.htmlparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UseClient {
    private Client client = ClientBuilder.newClient();

    private static final Logger LOGGER = LoggerFactory.getLogger(UseClient.class);


    public String getPage(String url) {
        LOGGER.info("getPage start. url: {}", url);
        WebTarget target = client.target(url);
        Response response = target.request(MediaType.TEXT_HTML_TYPE).get();
        if (Response.Status.OK.getStatusCode() == response.getStatus()) {
            return response.readEntity(String.class);
        }
        if (Response.Status.FOUND.getStatusCode()==response.getStatus()) {
            return getPage(response.getHeaderString(HttpHeaders.LOCATION));
        }
        throw new WebApplicationException(response.getStatus());
    }


}
