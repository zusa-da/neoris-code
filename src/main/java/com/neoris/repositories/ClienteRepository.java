package com.neoris.repositories;

import com.neoris.entities.Cliente;
import io.quarkus.mongodb.panache.PanacheMongoRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Status;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheMongoRepository<Cliente> {

// put your custom logic here as instance methods

    public Cliente findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<Cliente> findStatus() {
        return list("status", true);
    }

    public void deleteLoics() {
        delete("name", "Loïc");
    }
}