package org.ashcheulov;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;

@Path("/recover")
public class RecoverPage {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public File getLoginPage() {
        return new File("../src/main/resources/META-INF/resources/recover.html");
    }
}
