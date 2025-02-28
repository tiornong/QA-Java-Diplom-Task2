package LoginUserEndpoint;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.bodytoget.AuthorizationInfo;
import model.UserToSend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static utils.Constants.*;

@DisplayName("Тест логина корректного пользователя")
public class LogInRealUserTest {

    private String authToken;
    private UserToSend userToSend;

    @BeforeEach
    @Step("Создание пользователя и сохранение токена авторизации")
    public void setUp() {
        StellarBurgerClient client = new StellarBurgerClient();
        userToSend = new UserToSend(TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME);
        authToken = client.createUser(userToSend).extract().as(AuthorizationInfo.class).getAccessToken();
    }

    @AfterEach
    public void tearDown() {
        StellarBurgerClient client = new StellarBurgerClient();
        client.deleteUser(authToken);
    }

    @Test
    @DisplayName("Логин существующим пользователем")
    public void logInRealUserTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        ValidatableResponse response = client.authorizeUser(userToSend);

        response.assertThat().statusCode(200);

    }

}
