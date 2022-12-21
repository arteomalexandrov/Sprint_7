package org.example;

import io.qameta.allure.junit4.DisplayName;
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



    @Test
    @DisplayName("Check response code for login courier")
    public void testCheckResponseForLoginCourier(){
        CourierClient courierClient = new CourierClient();
        Response loginCourierResponse = courierClient.loginCourierResponse(new
                AuthCourier(currentCourier.login,"*** NotLogin ***"));
        loginCourierResponse.statusCode(200).and.assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Check error message for not login")
    public void testErrorMessageForNotLogin(){
        CourierClient courierClient = new CourierClient();
        Response notLoginResponse = courierClient.notLoginResponse(new
                AuthCourier(currentCourier.login,"*** NotLogin ***"));
        notLoginResponse.statusCode(400).and.assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for not password")
    public void testErrorMessageForNotPassword(){
        CourierClient courierClient = new CourierClient();
        Response notPasswordResponse = courierClient.notPasswordResponse(new
                AuthCourier(currentCourier.login,"*** NotLogin ***"));
        notPasswordResponse.statusCode(400).and.assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for not login and not password")
    public void testErrorMessageForNotLoginAndNotPassword(){
        CourierClient courierClient = new CourierClient();
        Response notLoginAndNotPasswordResponse = courierClient.notLoginAndNotPasswordResponse(new
                AuthCourier(currentCourier.login,"*** NotLoginAndNotPassword ***"));
        notLoginAndNotPasswordResponse.statusCode(400).and.assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for incorrect login")
    public void testErrorMessageForIncorrectLogin(){
        CourierClient courierClient = new CourierClient();
        Response incorrectLoginResponse = courierClient.getIncorrectLoginResponse(new
                AuthCourier(currentCourier.login,"*** WrongLogin ***"));
        incorrectLoginResponse.statusCode(404).and.assertThat().body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check error message for incorrect password")
    public void testErrorMessageForIncorrectPassword(){
        CourierClient courierClient = new CourierClient();
        Response incorrectPasswordResponse = courierClient.getIncorrectPasswordResponse(new
                AuthCourier(currentCourier.login,"*** WrongPassword ***"));
        incorrectPasswordResponse.statusCode(404).and.assertThat().body("message", is("Учетная запись не найдена"));
    }

}
