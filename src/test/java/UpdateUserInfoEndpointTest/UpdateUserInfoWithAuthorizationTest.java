package UpdateUserInfoEndpointTest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.bodytoget.AuthorizationInfo;
import model.UserInfoToGet;

import model.bodytosend.UserToSend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.SC_OK;
import static utils.Constants.*;

@DisplayName("Тесты изменения информации о пользователе с авторизацией")
public class UpdateUserInfoWithAuthorizationTest {

    String authToken;

    @BeforeEach
    @Step("Создание пользователя и сохранение токена авторизации")
    void setUp() {
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend(TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME);
        authToken = client.createUser(userToSend).extract().as(AuthorizationInfo.class).getAccessToken();
    }

    @AfterEach
    public void tearDown() {
        StellarBurgerClient client = new StellarBurgerClient();
        client.deleteUser(authToken);
    }


    @Test
    @DisplayName("Обновление имени пользователя")
    public void updateUserNameTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserInfoToGet userInfo = client.getUserInfo(authToken).extract().as(UserInfoToGet.class);
        UserToSend userChangedInfo = new UserToSend(userInfo.getUser().getEmail(), TEST_USER_PASSWORD, "new-test-name");

        ValidatableResponse response = client.updateUserInfo(userChangedInfo, authToken);
        response.assertThat().statusCode(SC_OK);
    }

    @Test
    @DisplayName("Обновление емейла пользователя")
    public void updateUserEmailTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserInfoToGet userInfo = client.getUserInfo(authToken).extract().as(UserInfoToGet.class);
        UserToSend userChangedInfo = new UserToSend("new-test-name", TEST_USER_PASSWORD, userInfo.getUser().getName());

        ValidatableResponse response = client.updateUserInfo(userChangedInfo, authToken);
        response.assertThat().statusCode(SC_OK);
    }

}
