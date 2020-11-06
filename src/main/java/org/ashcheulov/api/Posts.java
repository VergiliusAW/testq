package org.ashcheulov.api;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("api")
public class Posts {

    @GET
    @Path("{id}")
    public JsonObject getPost(@PathParam("id") int id) {
        return DBService.getPostById(id);
    }

    @GET
    @Path("posts")
    public JsonArray getPosts() {
        return DBService.getPosts();
    }
}
