package CreateUserEndpointTest;

import io.restassured.response.ValidatableResponse;
import model.bodytoget.AuthorizationInfo;
import model.UserToSend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.SC_CREATED;
import static utils.Constants.*;

@DisplayName("Создание уникального пользователя")
public class CreateUniqueUserTest {

    private String authToken;

    @AfterEach
    public void tearDown() {
        StellarBurgerClient client = new StellarBurgerClient();
        client.deleteUser(authToken);
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUniqueUserTest() {
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend(TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME);
        ValidatableResponse response = client.createUser(userToSend);

        authToken = response.extract().as(AuthorizationInfo.class).getAccessToken();

        response.assertThat().statusCode(SC_CREATED);
        AuthorizationInfo authorizationInfo = response.extract().as(AuthorizationInfo.class);
        Assertions.assertNotNull(authorizationInfo);
    }
}
