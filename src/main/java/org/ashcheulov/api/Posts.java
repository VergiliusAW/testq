package org.ashcheulov.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("api")
public class Posts {

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
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject login(JsonObject s) {
        System.out.println(s.toString());
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("res","успех");
        return jsonObject;
    }
}
