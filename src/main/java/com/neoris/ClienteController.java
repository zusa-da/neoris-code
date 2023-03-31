package com.neoris;

import com.neoris.entities.Cliente;
import com.neoris.repositories.ClienteRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Consumes("application/json")
@Produces("application/json")
public class ClienteController {
    @Inject
    ClienteRepository clienteRepository;

    @GET
    public List<Cliente> list() {
        return clienteRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Cliente get(String id) {
        System.out.println("get =>" + id);
        return clienteRepository.findById(new ObjectId(id));
    }

    @POST
    public Response create(Cliente cliente) {
        clienteRepository.persist(cliente);
        return Response.status(201).build();
    }

    @PUT
    @Path("/{id}")
    public void update(String id, Cliente cliente) {
        clienteRepository.update(cliente);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        Cliente cliente = clienteRepository.findById(new ObjectId(id));
        clienteRepository.delete(cliente);
    }

    @GET
    @Path("/{name}")
    public Cliente search(@PathParam("name")String name) {
        return clienteRepository.findByName(name);
    }

    @DELETE
    public void deleteAll(){
        clienteRepository.deleteAll();
    }
}