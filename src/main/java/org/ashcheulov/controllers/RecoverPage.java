package org.ashcheulov.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Scanner;

@Path("/recover")
public class RecoverPage {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getRecoverPage() {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/recover.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).location(URI.create("/recover")).entity(text).build();
    }
}
