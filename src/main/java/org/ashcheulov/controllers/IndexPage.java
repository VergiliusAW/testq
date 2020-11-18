package org.ashcheulov.controllers;

import org.ashcheulov.models.DBService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.util.Scanner;

@Path("/")
public class IndexPage {

    @Inject
    DBService dbService;

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

    @GET
    @Path("post/{id}")
    public Response getPostPage(@PathParam("id") int id) {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/index.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).entity(text).build();
    }

    @GET
    @Path("profile")
    public Response getProfilePage() {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/index.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).entity(text).build();
    }

    @GET
    @Path("profile/{id}")
    public Response getProfilePageById(@PathParam("id") int id, @CookieParam("session") Cookie cookie) {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/index.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).entity(text).build();
    }
}