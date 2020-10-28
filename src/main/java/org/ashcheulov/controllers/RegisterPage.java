package org.ashcheulov.controllers;

import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.TemplateDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;
import java.util.Scanner;

@Path("/register")
public class RegisterPage {

    private final TemplateDB templateDB;

    @Autowired
    public RegisterPage(TemplateDB templateDB) {
        this.templateDB = templateDB;
    }

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

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response registerUser(JsonObject jsonObject) {
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        System.out.println(jsonObject.toString());
        if (templateDB.register(email,password)) {
            JsonObject json = new JsonObject();
            json.put("location","/profile");
            return Response.status(200).entity(json.toString()).build();
        }
        return Response.status(414).location(URI.create("/register")).build();
    }
}
