package org.ashcheulov.controllers;


import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;
import org.ashcheulov.models.tables.Users;

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

//        File f = new File("/home/mikhail/Документы");
//        f.isDirectory();
//        Arrays.stream(f.listFiles()).collect(Collectors.toList()).forEach(i -> System.out.println(i));
//        if (cookie != null)
//            if (cookie.getValue().equals("256fa90e-f71d-4667-b362-0c7d67d06724"))
//                return Response.ok("success").build();
//            else System.out.println(cookie.toString());
        return Response.ok("aaa").cookie(new NewCookie("session", UUID.randomUUID().toString())).build();
    }

    @GET
    @Path("img")
    public JsonObject getImg(@CookieParam("session") Cookie cookie) {
        JsonObject jsonObject = new JsonObject();
        if (cookie != null) {
            int id = dbService.checkSession(cookie);
            if (id != -1) {
                Users user = dbService.getUserById(id);
                jsonObject.put("session", true);
                jsonObject.put("img", user.getIco());
                jsonObject.put("href", user.getId());
                return jsonObject;
            } else System.out.println(cookie.toString());
        }

        jsonObject.put("session", false);
        return jsonObject;
    }

    @GET
    @Path("img/{id}")
    @Produces("image/png")
    public File getImgByLink(@PathParam("id") int id) {
        return new File(String.format("../src/main/resources/avatars/%s.jpg", id));
    }

    @GET
    @Path("UUID/{UUID}")
    public Response getUUID(@PathParam("UUID") UUID uuid) {
        return Response.ok(uuid.toString()).build();
    }
}
