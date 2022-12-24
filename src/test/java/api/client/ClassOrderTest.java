package api.client;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class ClassOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check response for get list orders")
    public void testGetListOrdersAndCheckResponse() {
        OrdersClient ordersClient = new OrdersClient();
        Response getListOrdersResponse = ordersClient.getListOrdersAndCheckResponse();
        getListOrdersResponse.then().statusCode(200).and()
                .assertThat().body("orders", notNullValue());
        System.out.println(getListOrdersResponse.body().asString());
    }
}