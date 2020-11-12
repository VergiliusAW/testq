package org.ashcheulov.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("api")
public class API {

    @GET
    @Path("/post/{id}")
    public JsonObject getPost(@PathParam("id") int id) {
        return DBService.getPostById(id);
    }

    @GET
    @Path("posts")
    public JsonArray getPosts() {
        return DBService.getPosts();
    }


    @POST
    @Path("system")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response system(JsonObject s) {
        System.out.println(s.toString());
        switch (s.getString("type")) {
            case "login":
                return Response.ok(login(s)).build();
            case "register":
                return Response.ok(register(s)).build();
            case "recover":
                return Response.ok(recover(s)).build();
        }
        JsonObject jsonObject = new JsonObject();
        // sl – success Login; srg – success Register; src – success Recover;
        // er – error Login; erg – error register; erc – error Recover
        jsonObject.put("res","sl");
        return Response.ok(jsonObject).build();
    }

    private JsonObject login(JsonObject req) {
        JsonObject jsonObject = new JsonObject();
        if (DBService.login())
            jsonObject.put("res","sl");
        else
            jsonObject.put("res","er");
        return jsonObject;
    }

    private JsonObject register(JsonObject req) {
        JsonObject jsonObject = new JsonObject();
        if (DBService.register())
            jsonObject.put("res","srg");
        else
            jsonObject.put("res","erg");
        return jsonObject;
    }
    private JsonObject recover(JsonObject req) {
        JsonObject jsonObject = new JsonObject();
        if (DBService.recover())
            jsonObject.put("res","src");
        else
            jsonObject.put("res","erc");
        return jsonObject;
    }
}
