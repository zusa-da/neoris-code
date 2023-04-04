package com.neoris;

import com.neoris.exception.MsgError;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Throwable>{

    @Override
    public Response toResponse(Throwable e) {


        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(new MsgError(e.getMessage(), false)).build();
    }

}
