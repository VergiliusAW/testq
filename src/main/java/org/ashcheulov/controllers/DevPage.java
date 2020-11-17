package org.ashcheulov.controllers;


import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.UUID;

@Path("dev")
public class DevPage {

    @Inject
    DBService dbService;

    @GET
    public Response getDevPage(@CookieParam("session") Cookie cookie) {
//        if (cookie != null)
//            if (cookie.getValue().equals("256fa90e-f71d-4667-b362-0c7d67d06724"))
//                return Response.ok("success").build();
//            else System.out.println(cookie.toString());
        return Response.ok(dbService.login(new JsonObject())).cookie(new NewCookie("session", UUID.randomUUID().toString())).build();
    }

    @GET
    @Path("img")
    @Produces("image/png")
    public File getImg(@CookieParam("session") Cookie cookie) {
//        return null;
        if (cookie != null)
            if (cookie.getValue().equals("256fa90e-f71d-4667-b362-0c7d67d06724")) //favicon.ico
                return new File("../src/main/resources/favicon.ico");
            else System.out.println(cookie.toString());
        return new File("../src/main/resources/logo192.png");
    }

    @GET
    @Path("UUID/{UUID}")
    public Response getUUID(@PathParam("UUID") UUID uuid) {
        return Response.ok(uuid.toString()).build();
    }
}
