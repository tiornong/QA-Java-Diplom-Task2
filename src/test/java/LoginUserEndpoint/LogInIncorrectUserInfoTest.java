package LoginUserEndpoint;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.AuthorizationInfo;
import model.UserToSend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static utils.Constants.*;

@DisplayName("Тесты логина пользователя с некорректными данными")
public class LogInIncorrectUserInfoTest {

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
    @DisplayName("Попытка логина без емейла")
    public void logInIncorrectUserEmailTest() {
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend user = userToSend.copy();
        user.setEmail("");

        ValidatableResponse response = client.authorizeUser(user);
        response.assertThat().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @DisplayName("Попытка логина без пароля")
    public void logInIncorrectUserPasswordTest() {
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend user = userToSend.copy();
        user.setPassword("");

        ValidatableResponse response = client.authorizeUser(user);
        response.assertThat().statusCode(SC_UNAUTHORIZED);
    }

}
