package com.neoris;

import com.neoris.entities.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import static org.hamcrest.core.IsNot.not;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import io.restassured.parsing.Parser;
import static io.restassured.config.LogConfig.logConfig;
import io.restassured.config.ObjectMapperConfig;

@QuarkusTest
public class ClienteControllerTest {

    /*@BeforeAll
    static void initAll() {
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.config
                .logConfig((logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory((type, s) -> new ObjectMapper()
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule())
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)));
    }*/

    @Test
    void testRepository() {
        performTest("/cliente");
    }

    private void performTest(String path) {
        String cliente1 = "{ \"name\" : \"daniel\", \"birth\" : 1993, \"status\" : true}";
        String cliente2 = "{ \"name\" : \"emilio\", \"birth\" : 1988, \"status\" : true}";
        String cliente3 = "{ \"name\" : \"omar\", \"birth\" :1993, \"status\" : true}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cliente1)
                .when()
                .post(path)
                .then()
                .statusCode(201);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cliente2)
                .when()
                .post(path)
                .then()
                .statusCode(201);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cliente3)
                .when()
                .post(path)
                .then()
                .statusCode(201);

        Cliente[] clientes = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(path)
                .then()
                .statusCode(200)
                .extract()
                .body().as(Cliente[].class);

        Assertions.assertThat(clientes.length).isEqualTo(3);

        System.out.println("ObjectId: " + clientes[0].id.toString());
        Cliente cliente = RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .get(path + "/{id}", clientes[0].id.toString())
                .then()
                .statusCode(200)
                .extract()
                .body().as(Cliente.class);

        Assertions.assertThat(cliente.id).isEqualTo(clientes[0].id);
        Assertions.assertThat(cliente.name).isEqualTo(clientes[0].name);
        Assertions.assertThat(cliente.birth).isEqualTo(clientes[0].birth);
        Assertions.assertThat(cliente.status).isEqualTo(clientes[0].status);

        /*cliente = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(path + "/search/{name}", "moncef")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Cliente.class);

        Assertions.assertThat(cliente.name).isEqualTo("MONCEF");
        Assertions.assertThat(cliente.birth).isEqualTo(clientes[0].birth);
        Assertions.assertThat(cliente.status).isEqualTo(false);*/


        RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .delete(path + "/{id}", cliente.id.toString())
                .then()
                .statusCode(204);

        clientes = RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .get(path)
                .then()
                .statusCode(200)
                .extract()
                .body().as(Cliente[].class);

        Assertions.assertThat(clientes.length).isEqualTo(2);

        RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .delete(path)
                .then()
                .statusCode(204);
    }

}