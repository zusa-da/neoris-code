package com.neoris;

import com.neoris.entities.Cliente;
import com.neoris.service.ClienteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cliente")
@Consumes("application/json")
@Produces("application/json")
public class ClienteController {
    @Inject
    ClienteService clienteService;

    @GET
    public List<Cliente> list() {
        return clienteService.listAll();
    }

    @GET
    @Path("/{id}")
    public Cliente get(@PathParam("id") String id) {
        return clienteService.findById(id);
    }

    @POST
    public Response create(Cliente cliente) {
        clienteService.persist(cliente);
        return Response.status(201).build();
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, Cliente cliente) {
        clienteService.update(id, cliente);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        clienteService.delete(id);
    }

    @DELETE
    public void deleteAll(){
        clienteService.deleteAll();
    }

    /*@GET
    @Path("/{name}")
    public Cliente search(@PathParam("name")String name) {
        return clienteService.findByName(name);
    }
*/

}