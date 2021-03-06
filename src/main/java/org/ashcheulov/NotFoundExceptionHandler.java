package org.ashcheulov;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Scanner;

/**
 * NotFoundExceptionMapper
 */
@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        String text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/errors/404.html"), "UTF-8").useDelimiter("\\A").next();
        return Response.status(404).entity(text).build();
    }
}
