package org.ashcheulov;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Scanner;

@Path("/login")
public class LoginPage {

    @GET
    public Response getLoginPage() {
//        return new File("../src/main/resources/META-INF/resources/login.html");
        String text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/login.html"), "UTF-8").useDelimiter("\\A").next();
        return Response.status(200).entity(text).build();
    }
}
