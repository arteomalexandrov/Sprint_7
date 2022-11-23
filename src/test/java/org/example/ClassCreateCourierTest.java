package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ClassCreateCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void createNewCourierAndCheckResponse(){
        Card card = new Card("ninjacourier" + new Random().nextInt(100),
                "1234", "saske");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(card)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    public void createNewCourierWithRepeatedLogin() {
        File json = new File("src/test/resources/newCourier.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    public void createNewCourierWhenNotLogin(){
        File json = new File("src/test/resources/notLogin.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    public void createNewCourierWhenNotPassword(){
        File json = new File("src/test/resources/notLogin.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2("подставь_сюда_свой_токен")
                        .and()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

}
