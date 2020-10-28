package org.ashcheulov.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.TemplateDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Scanner;

@Path("/login")
public class LoginPage {

    private final TemplateDB templateDB;

    @Autowired
    public LoginPage(TemplateDB templateDB) {
        this.templateDB = templateDB;
    }

    @GET
    public Response getLoginPage() {
        String text;
        try {
            text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/login.html"), "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            throw new InternalServerErrorException();
        }
        return Response.status(200).entity(text).type(MediaType.TEXT_HTML).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(JsonObject jsonObject) {
        System.out.println(jsonObject.toString());
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        if (templateDB.login(email,password)) {
            System.out.println("user login");
            JsonObject json = new JsonObject();
            json.put("location","/profile");
            Cookie c = new Cookie(password, email);
            return Response.status(200).entity(json.toString()).cookie(new NewCookie(c)).build();
        } else
            return Response.status(403).contentLocation(URI.create("/login")).build();
    }
}
