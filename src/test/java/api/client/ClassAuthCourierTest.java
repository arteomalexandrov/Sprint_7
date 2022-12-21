package api.client;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class ClassAuthCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check response code for login courier")
    public void testCheckResponseForLoginCourier(){
        CourierClient courierClient = new CourierClient();
        Response loginCourierResponse = courierClient.loginCourierResponse(new
                Auth("1122ninja", "1234"));
        loginCourierResponse.then().statusCode(200).and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Check error message for not login")
    public void testErrorMessageForNotLogin(){
        CourierClient courierClient = new CourierClient();
        Response notLoginResponse = courierClient.notLoginResponse(new
                Auth("","1234"));
        notLoginResponse.then().statusCode(400).and()
                .assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for not password")
    public void testErrorMessageForNotPassword(){
        CourierClient courierClient = new CourierClient();
        Response notPasswordResponse = courierClient.notPasswordResponse(new
                Auth("ninja",""));
        notPasswordResponse.then().statusCode(400).and()
                .assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for not login and not password")
    public void testErrorMessageForNotLoginAndNotPassword(){
        CourierClient courierClient = new CourierClient();
        Response notLoginAndNotPasswordResponse = courierClient.notLoginAndNotPasswordResponse(new
                Auth("",""));
        notLoginAndNotPasswordResponse.then().statusCode(400).and()
                .assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check error message for incorrect login")
    public void testErrorMessageForIncorrectLogin(){
        CourierClient courierClient = new CourierClient();
        Response incorrectLoginResponse = courierClient.getIncorrectLoginResponse(new
                Auth("fhgfjdfhh","4321"));
        incorrectLoginResponse.then().statusCode(404).and()
                .assertThat().body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check error message for incorrect password")
    public void testErrorMessageForIncorrectPassword(){
        CourierClient courierClient = new CourierClient();
        Response incorrectPasswordResponse = courierClient.getIncorrectPasswordResponse(new
                Auth("ninja","4321"));
        incorrectPasswordResponse.then().statusCode(404).and()
                .assertThat().body("message", is("Учетная запись не найдена"));
    }

}