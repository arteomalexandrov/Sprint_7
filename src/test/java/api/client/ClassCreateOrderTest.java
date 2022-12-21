package api.client;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class ClassCreateOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check response for create new order")
    public void testCreateNewOrderAndCheckResponse(){
        OrdersClient ordersClient = new OrdersClient();
        Response createOrderResponse = ordersClient.createNewOrderAndCheckResponse();
        createOrderResponse.then().statusCode(201).and()
                .assertThat().body("track", notNullValue());
    }

    @Test
    @DisplayName("Check response for create new order black and gray")
    public void testCreateNewOrderBlackAndGray(){
        OrdersClient ordersClient = new OrdersClient();
        Response createOrderBlackAndGrayResponse = ordersClient.createNewOrderBlackAndGray();
        createOrderBlackAndGrayResponse.then().statusCode(201).and()
                .assertThat().body("track", notNullValue());
    }

    @Test
    @DisplayName("Check response for create new order not colors")
    public void testCreateNewOrderNotColors(){
        OrdersClient ordersClient = new OrdersClient();
        Response createOrderNotColorsResponse = ordersClient.createNewOrderNotColors();
        createOrderNotColorsResponse.then().statusCode(201).and()
                .assertThat().body("track", notNullValue());
    }

}