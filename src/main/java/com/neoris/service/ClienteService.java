package com.neoris.service;

import com.neoris.entities.Cliente;
import com.neoris.exception.ApiException;
import com.neoris.repositories.ClienteRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ClienteService {

    @ConfigProperty(name = "com.neoris.error.msg.badrequest.numeric")
    String idNumericErrorMsg;

    @Inject
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listAll() {
        return clienteRepository.listAll();
    }

    public Cliente findById(String id) {

        Cliente cliente = clienteRepository.findById(new ObjectId(id));

        if (cliente != null) {
            return cliente;
        }

        throw new ApiException("ID de Cliente no encontrado: " + id.toString());

    }

    public void persist(Cliente Cliente) {
        Cliente cliente = clienteRepository.findByName(Cliente.getName());

        if (cliente != null) {
            throw new ApiException("El nombre del Cliente ya existe: " + cliente.getId().toString());
        } else {
            clienteRepository.persist(Cliente);
        }
    }

    public void update(String id, Cliente cliente) {
        Cliente clienteTemp = clienteRepository.findById(new ObjectId(id));

        if (clienteTemp != null) {
            clienteRepository.update(cliente);
        } else {
            throw new ApiException("El ID de Cliente no existe: " + id);
        }
    }

    public void delete(String id) throws ApiException {
        try {

            Cliente cliente = clienteRepository.findById(new ObjectId(id));
            clienteRepository.delete(cliente);

            clienteRepository.delete(cliente);

        } catch (NumberFormatException e) {
            throw new ApiException(idNumericErrorMsg);
        } catch (Exception e) {
            throw new ApiException("El ID de Cliente no existe o no pudo ser eliminado: " + id);
        }
    }

    public void deleteAll(){
        clienteRepository.deleteAll();
    }



    /*public List<Cliente> findByName(String name) {
        return clienteRepository.findByName(name);
    }*/
}
