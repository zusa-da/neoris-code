package com.neoris;

import com.neoris.service.ProductService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNot.not;

@QuarkusTest
public class ProductControllerTest{


    @Test
    void testListAllProducts() {
        //Get All
        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body(
                        containsString("TDC"),
                        containsString("CA"),
                        containsString("CH")
                );

        //GetBy ID
        given()
                .when().get("/product/1")
                .then()
                .statusCode(200)
                .body(
                        containsString("TDC")
                );

        //Delete
        given()
                .when().delete("/product/1")
                .then()
                .statusCode(204)
        ;

        //List all
        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body(
                        not(containsString("TDC")),
                        containsString("CA"),
                        containsString("CH")
                );

        //Create
        String user = "{\"id\": 6, \"name\":\"Inversion Moneda EUA\",\"description\":\"IME\"}";
        given().
                contentType(ContentType.JSON)
                .body(user)
                .when().post("/product")
                .then()
                .statusCode(201)
                .body(IsEmptyString.emptyString());

        //List all
        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body(
                        containsString("IME"),
                        containsString("CA"),
                        containsString("CH")
                );
    }


    /*
    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    void testFindByColor() {
        //Find by color that no fruit has
        given()
                .when().get("/fruits/color/Black")
                .then()
                .statusCode(200)
                .body("size()", is(0));

        //Find by color that multiple fruits have
        given()
                .when().get("/fruits/color/Red")
                .then()
                .statusCode(200)
                .body(
                        containsString("Apple"),
                        containsString("Strawberry")
                );

        //Find by color that matches
        given()
                .when().get("/fruits/color/Green")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body(containsString("Avocado"));

        //Update
        given()
                .when().put("/fruits/id/4/color/Black")
                .then()
                .statusCode(200)
                .body(containsString("Black"));

        //Find by color that Avocado now has
        given()
                .when().get("/fruits/color/Black")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body(
                        containsString("Black"),
                        containsString("Avocado")
                );
    }*/

}