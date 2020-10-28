package org.ashcheulov.controllers.errors;

import org.springframework.stereotype.Controller;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Scanner;

/**
 * InternalServerErrorExceptionMapper
 */
@Controller
@Provider
public class InternalServerErrorExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {

        int code = 500;
        if (exception instanceof WebApplicationException) {
            code = ((WebApplicationException) exception).getResponse().getStatus();
        }

        String text = new Scanner(this.getClass().getResourceAsStream("/META-INF/resources/www/views/500.html"), "UTF-8").useDelimiter("\\A").next();
        return Response.status(code).entity(text).build();
    }
}
