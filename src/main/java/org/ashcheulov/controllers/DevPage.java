package org.ashcheulov.controllers;


import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

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
//        return null;
        JsonObject jsonObject = new JsonObject();
        if (cookie != null) {
            if (dbService.checkSession(cookie)) {
                jsonObject.put("session", true);
                jsonObject.put("href", "/dev/img/1");
                return jsonObject;
            }
            else System.out.println(cookie.toString());
        }
//            if (cookie.getValue().equals("e1403c79-12a5-4f62-96af-31ec1662144f")) {
//                jsonObject.put("session",true);
//                jsonObject.put("href","/dev/img/1");
//                return jsonObject;
//            }

        jsonObject.put("session", false);
        return jsonObject;
    }

    @GET
    @Path("img/1")
    @Produces("image/png")
    public File getImgByLink() {
        return new File("../src/main/resources/favicon.ico");
    }

    @GET
    @Path("UUID/{UUID}")
    public Response getUUID(@PathParam("UUID") UUID uuid) {
        return Response.ok(uuid.toString()).build();
    }
}
