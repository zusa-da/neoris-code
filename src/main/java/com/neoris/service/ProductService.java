package com.neoris.service;

import com.neoris.entities.Product;
import com.neoris.exception.ApiException;
import com.neoris.repositories.ProductRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProductService {

    @ConfigProperty(name = "com.neoris.error.msg.badrequest.numeric")
    String idNumericErrorMsg;

    @Inject
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        Long idPro;
        try {
            idPro = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ApiException(idNumericErrorMsg);
        }

        Optional<Product> optional = productRepository.findById(idPro);

        if (optional.isPresent()) {
            return  optional.get();
        }

        throw new ApiException("ID de producto no encontrado: "+id.toString());
    }

    public Product create(Product product) {
        Optional<Product> optional = productRepository.findById(product.getId());

        if (optional.isPresent()) {
            throw new ApiException("El ID de producto ya existe: "+product.getId().toString());
        }
        return productRepository.save(product);
    }

    public void delete(String id)throws ApiException {
        Long idPro;
        try {
            idPro = Long.parseLong(id);
            productRepository.deleteById(idPro);

        } catch (NumberFormatException e) {
            throw new ApiException(idNumericErrorMsg);
        }catch (Exception e){
            throw new ApiException("El ID de producto no existe o no pudo ser eliminado: "+id);
        }
    }

    public Product update(Product product) {
        Optional<Product> optional = productRepository.findById(product.getId());
        if (optional.isPresent()) {
            return productRepository.save(product);
        }

        throw new ApiException("El ID de producto no existe: "+product.getId());
    }


    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

}
