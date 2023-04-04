package com.neoris.services;

import com.neoris.entities.Product;
import com.neoris.repositories.ProductRepository;
import com.neoris.service.ProductService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@QuarkusTest
public class ProductServiceTest {

    @InjectMock
    ProductRepository productRepository;

    @Inject
    ProductService productService;

    @Test
    void whenGetAllProducts() {
        Product p1= new Product();
        p1.setId(1l);
        p1.setName("Tarjeta de Credito");
        p1.setDescription("TC");

        Product p2= new Product();
        p2.setId(2l);
        p2.setName("Tarjeta de Debito");
        p2.setDescription("TD");

        List listProduct = new ArrayList(Arrays.asList(p1, p2));


        when(productRepository.findAll())
                .thenReturn(listProduct);
        assertEquals(2, productService.findAll().size());
    }

    @Test
    void whenGetProductById() {
        Product p1= new Product();
        p1.setId(1l);
        p1.setName("Tarjeta de Credito");
        p1.setDescription("TC");
        Optional<Product> Op= Optional.of(p1);

        when(productRepository.findById(Long.parseLong("1")))
                .thenReturn(Op);

        assertEquals(1, productService.findById("1").getId());
    }

    @Test
    void whenCreateProduct() {
        Product p3= new Product();
        p3.setId(3l);
        p3.setName("Credito Auto");
        p3.setDescription("CA");
        Optional<Product> Op= Optional.of(p3);

        when(productRepository.save(p3))
                .thenReturn(p3);

        assertEquals(3, productService.create(p3).getId());
    }

    @Test
    void whenUpdateProduct() {
        Product p3= new Product();
        p3.setId(3l);
        p3.setName("Credito auto");
        p3.setDescription("CA");
        Optional<Product> Op= Optional.of(p3);

        when(productRepository.save(p3))
                .thenReturn(p3);

        when(productRepository.findById(Long.parseLong("3")))
                .thenReturn(Op);

        p3.setName("Credito Auto");

        assertEquals("Credito Auto", productService.update(p3).getName());
    }

    @Test
    void whenDeleteProduct() {
        Product p3= new Product();
        p3.setId(3l);
        p3.setName("Credito auto");
        p3.setDescription("CA");
        Optional<Product> Op= Optional.of(p3);

        when(productRepository.save(p3))
                .thenReturn(p3);

        when(productRepository.findById(Long.parseLong("3")))
                .thenReturn(Op);

        p3.setName("Credito Auto");

        /*assertThrows(
                ProductException.class,
                () ->productService.delete("3"));*/

        assertDoesNotThrow(() ->productService.delete("3"));

        //assertEquals("El ID de producto no existe o no pudo ser eliminado: "+p3.getId(), ex.getMessage());
    }

}
