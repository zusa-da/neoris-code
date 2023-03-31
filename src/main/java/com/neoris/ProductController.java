package com.neoris;

import com.neoris.entities.Product;
import com.neoris.exception.ProductException;
import com.neoris.repositories.ProductRepository;
import com.neoris.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController{

    @Inject
    ProductService ps;

    @GET
    public List<Product> list(){
        return ps.findAll();
    }

    @GET
    @Path("{id}")
    public Product getProductById(@PathParam("id") String id) throws ProductException {
        //@See GeneralExceptionHandler
            return ps.findById(id);
    }

    @POST
    public Response create(Product p){
        ps.create(p);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id){
        ps.delete(id);
        return Response.noContent().build();
    }

    @PATCH
    public Response update(Product p){
        ps.update(p);
        return Response.ok().build();
    }



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}