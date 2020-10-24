package org.ashcheulov.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Scanner;

@Path("/")
public class IndexPage {

    @GET
    public Response getIndexPage() {
        String text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/index.html"), "UTF-8").next();
        return Response.status(200).entity(text).build();
//        return new File("../src/main/resources/META-INF/resources/www/views/index.html");
    }
}