package org.ashcheulov.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Scanner;

@Path("/register")
public class RegisterPage {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getRegisterPage() {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/register.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).entity(text).build();
    }
}
