package com.shankulk.exception.mapper;

import com.shankulk.exception.UnsupportedCurrencyException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.eclipse.jetty.http.HttpStatus;

@Provider
public class UnsupportedCurrencyExceptionMapper implements ExceptionMapper<UnsupportedCurrencyException> {

    public Response toResponse(UnsupportedCurrencyException exception) {
        return Response.status(HttpStatus.NOT_ACCEPTABLE_406)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}