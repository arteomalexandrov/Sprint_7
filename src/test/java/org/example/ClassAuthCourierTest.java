package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ClassAuthCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void authCourierAndCheckResponse(){
        File json = new File("src/test/resources/loginCourier.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    public void authCourierWhenNotLogin(){
        File json = new File("src/test/resources/authNotLogin.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void authCourierWhenNotPassword(){
        File json = new File("src/test/resources/authNotPassword.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void authCourierWhenNotLoginAndNotPassword(){
        File json = new File("src/test/resources/authNotLoginAndNotPassword.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    public void authCourierWhenIncorrectLogin(){
        File json = new File("src/test/resources/authIncorrectLogin.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    public void authCourierWhenIncorrectPassword(){
        File json = new File("src/test/resources/authIncorrectPassword.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier/login");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

}
