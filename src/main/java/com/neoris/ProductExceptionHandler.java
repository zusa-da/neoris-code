package com.neoris;

import com.neoris.exception.ProductError;
import com.neoris.exception.ProductException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/*public class ProductExceptionHandler
{}
*/@Provider
public class ProductExceptionHandler implements ExceptionMapper<ProductException> {

    @ConfigProperty(name = "com.neoris.error.msg.usernotfound")
    String userNotFound;

    @Override
    public Response toResponse(ProductException e) {

        if (e.getMessage().equalsIgnoreCase(userNotFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity(new ProductError(e.getMessage(), false)).build();
        } else {

            return Response.status(Response.Status.BAD_REQUEST).
                    entity(new ProductError(e.getMessage(), false)).build();
        }
    }

}
