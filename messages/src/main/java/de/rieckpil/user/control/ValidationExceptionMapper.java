/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.user.control;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Philip
 */
@Provider
public class ValidationExceptionMapper implements
        ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Response.ResponseBuilder builder
                = Response.status(Response.Status.BAD_REQUEST);
        exception.getConstraintViolations()
                .forEach(v -> {
                    builder.header("Error-Description", v
                    );
                });
        return builder.build();
    }
}
