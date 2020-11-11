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
    public Response login(JsonObject s) {
        System.out.println(s.toString());
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("res",true);
        return Response.ok(jsonObject).build();
    }
}
