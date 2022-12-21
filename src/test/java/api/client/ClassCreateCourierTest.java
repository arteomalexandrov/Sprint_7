package api.client;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.Matchers.*;

public class ClassCreateCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check response for create new courier")
    public void testCheckResponseForCreateCourier(){
        CourierClient courierClient = new CourierClient();
        Response createCourierResponse = courierClient.checkResponseForCreateCourier(new
                Courier("ninjacourier" + new Random().nextInt(100),
                "1234", "saske"));
        createCourierResponse.then().statusCode(201).and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Check response for create new courier with repeated login")
    public void testCreateNewCourierWithRepeatedLogin(){
        CourierClient courierClient = new CourierClient();
        Response createCourierWithRepeatLoginResponse = courierClient.createNewCourierWithRepeatedLogin(new
                Courier("ninja", "1234", "saske"));
        createCourierWithRepeatLoginResponse.then().statusCode(409).and()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Check response for create new courier when not login")
    public void testCreateNewCourierWhenNotLogin(){
        CourierClient courierClient = new CourierClient();
        Response createCourierNotLoginResponse = courierClient.createNewCourierWhenNotLogin(new
                Courier("", "1234", "saske"));
        createCourierNotLoginResponse.then().statusCode(400).and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check response for create new courier when not password")
    public void testCreateNewCourierWhenNotPassword(){
        CourierClient courierClient = new CourierClient();
        Response createCourierNotPasswordResponse = courierClient.createNewCourierWhenNotPassword(new
                Courier("ninja", "", "saske"));
        createCourierNotPasswordResponse.then().statusCode(400).and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}