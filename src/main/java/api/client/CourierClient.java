package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String loginCourier = "/api/v1/courier/login";
    private static final String createCourier = "/api/v1/courier";
    private static final String deleteCourier = "/api/v1/courier/";

    @Step("Get response for incorrect password")
    public Response getIncorrectPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(loginCourier);
    }

    @Step("Get response for incorrect login")
    public Response getIncorrectLoginResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(loginCourier);
    }

    @Step("Get response for not login and not password")
    public Response notLoginAndNotPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(loginCourier);
    }

    @Step("Get response for not password")
    public Response notPasswordResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(loginCourier);
    }

    @Step("Get response for not login")
    public Response notLoginResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(loginCourier);
    }

    @Step("Get response for login courier")
    public Response loginCourierResponse(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(loginCourier);
    }

    @Step("Get response for create courier")
    public Response checkResponseForCreateCourier(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(createCourier);
    }

    @Step("Get response for create courier with repeated login")
    public Response createNewCourierWithRepeatedLogin(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(createCourier);
    }

    @Step("Get response for create new courier when not login")
    public Response createNewCourierWhenNotLogin(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(createCourier);
    }

    @Step("Get response for create new courier when not password")
    public Response createNewCourierWhenNotPassword(Object body) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(createCourier);
    }

    @Step("delete courier")
    public Response deleteCourier(String id) {
        return given()
                .header("Content-type", "application/json")
                .delete(deleteCourier + id);
    }
}