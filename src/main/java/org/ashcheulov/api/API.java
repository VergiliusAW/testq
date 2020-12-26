package org.ashcheulov.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.ashcheulov.models.DBService;
import org.ashcheulov.models.tables.Users;
import org.ashcheulov.utils.Helper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.UUID;


@Path("api")
public class API {

    @Inject
    DBService dbService;

    private NewCookie cookie;

    @Inject
    private Helper helper;

    @GET
    @Path("/post/{id}")
    public JsonObject getPost(@PathParam("id") int id) {
        return dbService.getPostById(id);
    }

    @GET
    @Path("posts")
    public JsonArray getPosts() {
        return dbService.getPosts();
    }


    @POST
    @Path("system")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response system(JsonObject s) {
        switch (s.getString("type")) {
            case "login":
                return login(s);
            case "register":
                return register(s);
            case "recover":
                return Response.ok(recover(s)).build();
            default:
                throw new WebApplicationException();
        }
    }

    @GET
    @Path("profile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@PathParam("id") int id, @CookieParam("session") Cookie cookie) {
        JsonObject jsonObject = new JsonObject();
        if (cookie!=null) {
            int user_id = dbService.checkSession(cookie);
            Users user = dbService.getUserById(id);
            jsonObject.put("res","allow");
            JsonArray jsonArray = new JsonArray();
            user.getPosts().forEach(post -> jsonArray.add(new JsonObject().put("title",post.getTitle())));
            jsonObject.put("body",jsonArray);
            JsonArray jA = new JsonArray();
            user.getPosts().forEach(post -> jA.add(new JsonObject().put("id",post.getId())));
            jsonObject.put("hrefs",jA);
            if (user_id == id) {
                jsonObject.put("cl",true);
                return Response.ok(jsonObject).build();
            } else {
                jsonObject.put("cl",false);
                return Response.status(403).entity(jsonObject).build();
            }
        } else {
            jsonObject.put("res","denied");
            jsonObject.put("body", "403 ОТКАЗАНО В ДОСТУПЕ");
            return Response.status(403).entity(jsonObject).build();
        }

    }

    @POST
    @Path("post/profile/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPost(@PathParam("id") int id,JsonObject s, @CookieParam("session") Cookie cookie) {
        System.out.println(s);
        JsonObject jsonObject = new JsonObject();
        if (!s.getString("body").equals("")) {
            dbService.addPost(id,s);
            jsonObject.put("res","success");
            return Response.ok(jsonObject).build();
        }
        jsonObject.put("res","tryAgain");

        return Response.ok(jsonObject).build();
    }

    private Response login(JsonObject req) {
        Response.ResponseBuilder responseBuilder = Response.ok();
        JsonObject jsonObject = new JsonObject();

        int id = dbService.login(req);

        if (id == -1) {
            jsonObject.put("res", "er");
        } else {
            jsonObject.put("res", "sl");
        }

        responseBuilder.entity(jsonObject).cookie(helper.getNewSessionCookie(id));

        return responseBuilder.build();
    }

    private Response register(JsonObject req) {
        Response.ResponseBuilder responseBuilder = Response.ok();

        JsonObject jsonObject = new JsonObject();
        int id = dbService.register(req);

        if (id == -1) {
            jsonObject.put("res", "er");
        } else {
            jsonObject.put("res", "sl");
        }

        responseBuilder.entity(jsonObject).cookie(helper.getNewSessionCookie(id));

        return responseBuilder.build();
    }

    private JsonObject recover(JsonObject req) {
        JsonObject jsonObject = new JsonObject();
        if (dbService.recover(req))
            jsonObject.put("res", "src");
        else
            jsonObject.put("res", "erc");
        return jsonObject;
    }
}
