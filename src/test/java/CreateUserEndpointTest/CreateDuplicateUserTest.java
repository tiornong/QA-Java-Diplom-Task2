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

@DisplayName("Проверка создания дубликата пользователя")
public class CreateDuplicateUserTest {

    private String authToken;

    @AfterEach
    public void tearDown() {
        StellarBurgerClient client = new StellarBurgerClient();
        client.deleteUser(authToken);
    }

    @Test
    @DisplayName("Создание дубликата пользователя")
    public void createDuplicateUserTest() {
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend(TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME);

        authToken = client.createUser(userToSend).extract().as(AuthorizationInfo.class).getAccessToken();

        ValidatableResponse response = client.createUser(userToSend);

        response.assertThat().statusCode(SC_FORBIDDEN);
    }
}
