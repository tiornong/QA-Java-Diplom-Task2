package GetOrdersByUserEndpointTest;

import io.restassured.response.ValidatableResponse;
import model.AuthorizationInfo;
import model.UserToSend;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static utils.Constants.*;

@DisplayName("Тесты получения списка заказов пользователя")
public class GetOrdersByUserTest {

    @Test
    @DisplayName("Получения списка заказов пользователя с авторизацией")
    public void getOrdersByUserWithAuthorizationTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserToSend userToSend = new UserToSend(TEST_USER_EMAIL, TEST_USER_PASSWORD, TEST_USER_NAME);
        String authToken = client.createUser(userToSend).extract().as(AuthorizationInfo.class).getAccessToken();


        ValidatableResponse response = client.getOrdersByUser(authToken);
        response.assertThat().statusCode(SC_OK);

        client.deleteUser(authToken);

    }

    @Test
    @DisplayName("Получения списка заказов пользователя без авторизации")
    public void getOrdersByUserWithoutAuthorizationTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        ValidatableResponse response = client.getOrdersByUser("");

        response.assertThat().statusCode(SC_UNAUTHORIZED);
    }
}
