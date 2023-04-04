package com.neoris;

import com.neoris.exception.MsgError;
import com.neoris.exception.ApiException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/*public class ProductExceptionHandler
{}
*/@Provider
public class ProductExceptionHandler implements ExceptionMapper<ApiException> {

    @ConfigProperty(name = "com.neoris.error.msg.usernotfound")
    String userNotFound;

    @Override
    public Response toResponse(ApiException e) {

        if (e.getMessage().equalsIgnoreCase(userNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new MsgError(e.getMessage(), false)).build();
        } else {

            return Response.status(Response.Status.BAD_REQUEST).
                    entity(new MsgError(e.getMessage(), false)).build();
        }
    }

}
