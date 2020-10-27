package org.ashcheulov.controllers;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profile")
public class ProfilePage {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance profile();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getProfilePage() {
        System.out.println(Templates.profile().render());
        return Response.status(200).entity(Templates.profile().data("name", "Mikhail").render()).build();
    }
}
