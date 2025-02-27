package CreateUserEndpointTest;

import io.restassured.response.ValidatableResponse;
import model.AuthorizationInfo;
import model.UserToSend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static utils.Constants.*;

@DisplayName("Проверки создания пользователя с отсутствующими параметрами")
public class CreateUserWithLackInfoTest {
    private String authToken;

    @AfterEach
    public void tearDown() {
        StellarBurgerClient client = new StellarBurgerClient();
        client.deleteUser(authToken);
    }

    @Test
    @DisplayName("Попытка создания пользователя без почты")
    public void createUserWithLackEmailTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend("", TEST_USER_PASSWORD, TEST_USER_NAME);
        ValidatableResponse response = client.createUser(userToSend);

        try {
            authToken = response.extract().as(AuthorizationInfo.class).getAccessToken();
        } catch (Exception e) {
            authToken = "";
        }

        response.assertThat().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Попытка создания пользователя без пароля")
    public void createUserWithLackPasswordTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend(TEST_USER_EMAIL, "", TEST_USER_NAME);
        ValidatableResponse response = client.createUser(userToSend);

        try {
            authToken = response.extract().as(AuthorizationInfo.class).getAccessToken();
        } catch (Exception e) {
            authToken = "";
        }

        response.assertThat().statusCode(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Попытка создание пользователя без имени")
    public void createUserWithLackNameTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend(TEST_USER_EMAIL, TEST_USER_PASSWORD, "");
        ValidatableResponse response = client.createUser(userToSend);
        try {
            authToken = response.extract().as(AuthorizationInfo.class).getAccessToken();
        } catch (Exception e) {
            authToken = "";
        }
        response.assertThat().statusCode(SC_FORBIDDEN);
    }
}
