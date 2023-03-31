package com.neoris;

import com.neoris.exception.ProductError;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable e) {


        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(new ProductError(e.getMessage(), false)).build();
    }

}
