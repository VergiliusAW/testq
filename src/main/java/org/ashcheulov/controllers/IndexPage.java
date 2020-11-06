package org.ashcheulov.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Scanner;

@Path("/")
public class IndexPage {

    @GET
    public Response getIndexPage() {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/index.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).entity(text).build();
    }

//    @POST
//    public Response getIndexPageAfterPost() {
//        String text;
//        try {
//            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/index.html"), "UTF-8").useDelimiter("\\A").next();
//        } catch (NullPointerException e) {
//            throw new InternalServerErrorException();
//        }
//        return Response.status(200).entity(text).build();
//    }
}