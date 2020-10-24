package org.ashcheulov.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.sql.ResultSet;
import java.util.Scanner;

@Path("/recover")
public class RecoverPage {

    @GET
    public Response getRecover() {
        String text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/recover.html"), "UTF-8").next();
        return Response.status(200).entity(text).build();
    }
}
