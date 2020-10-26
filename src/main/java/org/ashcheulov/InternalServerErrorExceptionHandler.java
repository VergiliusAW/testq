package org.ashcheulov;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * InternalServerErrorExceptionMapper
 */
@Provider
public class InternalServerErrorExceptionHandler implements ExceptionMapper<InternalServerErrorException> {

    @Override
    public Response toResponse(InternalServerErrorException exception) {
        String text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/500.html"), "UTF-8").useDelimiter("\\A").next();
        return Response.status(500).entity(text).build();
    }
}
