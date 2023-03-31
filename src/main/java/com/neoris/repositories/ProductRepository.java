package com.neoris.repositories;

import com.neoris.entities.Product;
import org.springframework.data.repository.CrudRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public interface ProductRepository extends CrudRepository<Product, Long> {

    public List<Product> findByName(String color);

    @Override
    public Optional<Product> findById(Long color);

    @Override
    public List<Product> findAll();

}
