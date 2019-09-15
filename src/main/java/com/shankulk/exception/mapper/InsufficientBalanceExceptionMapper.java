package com.shankulk.exception.mapper;

import com.shankulk.exception.InsufficientBalanceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.eclipse.jetty.http.HttpStatus;

@Provider
public class InsufficientBalanceExceptionMapper implements ExceptionMapper<InsufficientBalanceException> {

    public Response toResponse(InsufficientBalanceException exception) {
        return Response.status(HttpStatus.NOT_ACCEPTABLE_406)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}