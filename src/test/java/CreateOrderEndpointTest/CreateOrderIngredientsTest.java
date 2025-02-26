package CreateOrderEndpointTest;

import io.qameta.allure.Step;
import model.AuthorizationInfo;
import model.UserToSend;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.StellarBurgerClient;

import static utils.Constants.*;

public class CreateOrderIngredientsTest {

    private String authToken;

    @BeforeEach
    @Step("Создание пользователя и сохранение токена авторизации")
    public void setUp() {
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
    public void createOrderWithIngredientsTest(){}

    @Test
    public void createOrderWithoutIngredientsTest(){}

    @Test
    public void createOrderWithIncorrectIngredientsCashesTest(){}
}
