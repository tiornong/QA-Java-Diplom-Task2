package utils;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;
import model.OrderInfoToSend;
import model.RefreshToken;
import model.UserToSend;

import static io.restassured.RestAssured.given;
import static utils.Constants.*;

public class StellarBurgerClient {

    @Step("Клиент -- получение списка ингредиентов")
    public ValidatableResponse getIngredients(){
        return given()
                .filter(new AllureRestAssured())
                .get(INGREDIENTS_URL)
                .then();
    }

    @Step("Клиент -- создание заказа")
    public ValidatableResponse createOrder(OrderInfoToSend order, String authToken){
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", authToken)
                .body(order)
                .post(ORDERS_URL)
                .then();
    }

    //public ValidatableResponse restorePassword(){}

    @Step("Клиент -- создание пользователя")
    public ValidatableResponse createUser(UserToSend user){
        return given()
                .filter(new AllureRestAssured())
                .body(user)
                .post(REGISTER_URL)
                .then();
    }

    @Step("Клиент -- авторизация пользователя")
    public ValidatableResponse authorizeUser(UserToSend user){
        return given()
                .filter(new AllureRestAssured())
                .body(user)
                .post(AUTHORIZATION_URL)
                .then();
    }

    public ValidatableResponse quitUser(RefreshToken refreshToken){
        return given()
                .filter(new AllureRestAssured())
                .body(refreshToken)
                .post(LOGOUT_URL)
                .then();
    }

    // Не пригодится в рамках проекта, поэтому скорее всего не будет реализован
    //public ValidatableResponse refreshUserToken(){}

    @Step("Клиент -- получение информации о пользователе")
    public ValidatableResponse getUserInfo(String authToken){
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", authToken)
                .get(USER_INFO_URL)
                .then();
    }

    @Step("Клиент -- обновление информации о пользователе")
    public ValidatableResponse updateUserInfo(String authToken, UserToSend user){
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", authToken)
                .body(user)
                .patch(USER_INFO_URL)
                .then();
    }

    @Step("Клиент -- удаление пользователя")
    public ValidatableResponse deleteUser(String authToken){
        return given()
                .header("Authorization", authToken)
                .delete(USER_INFO_URL)
                .then();
    }

    @Step
    public ValidatableResponse getAllOrders(){
        return given()
                .filter(new AllureRestAssured())
                .get(ALL_ORDERS_URL)
                .then();
    }

    public ValidatableResponse getOrdersByUser(String authToken){
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", authToken)
                .get(ORDERS_URL)
                .then();
    }

}
