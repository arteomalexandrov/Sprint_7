package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ClassAuthCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void authCourierAndCheckResponse(){
        Auth auth = new Auth("1122ninja",
                "1234");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(auth)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void authCourierWhenNotLogin(){
        Auth auth = new Auth("",
                "1234");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(auth)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void authCourierWhenNotPassword(){
        Auth auth = new Auth("ninja",
                "");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(auth)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void authCourierWhenNotLoginAndNotPassword(){
        Auth auth = new Auth("",
                "");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(auth)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void authCourierWhenIncorrectLogin(){
        Auth auth = new Auth("fhgfjdfhh",
                "1234");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(auth)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    public void authCourierWhenIncorrectPassword(){
        Auth auth = new Auth("ninja",
                "4321");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(auth)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

}
