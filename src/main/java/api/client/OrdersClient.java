package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class OrdersClient {

    private static final String orders = "/api/v1/orders";

    @Step("Get response for create new order")
    public Response createNewOrderAndCheckResponse() {
        return given()
                .multiPart(new File("src/test/resources/newOrder.json"))
                .when()
                .post(orders);
    }

    @Step("Get response for create new order black and gray")
    public Response createNewOrderBlackAndGray() {
        return given()
                .multiPart(new File("src/test/resources/newOrderBlackAndGray.json"))
                .when()
                .post(orders);
    }

    @Step("Get response for create new order not colors")
    public Response createNewOrderNotColors() {
        return given()
                .multiPart(new File("src/test/resources/newOrderNotColors.json"))
                .when()
                .post(orders);
    }

    @Step("Get response for get list orders")
    public Response getListOrdersAndCheckResponse() {
        return given()
                .get(orders);
    }
}