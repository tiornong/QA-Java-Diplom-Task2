package CreateOrderEndpointTest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.bodytoget.AuthorizationInfo;
import model.bodytoget.OrderSuccessConfirmation;
import model.bodytoget.StandardAnswer;
import model.bodytosend.OrderInfoToSend;
import model.bodytosend.UserToSend;
import org.junit.jupiter.api.*;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.*;
import static utils.Constants.*;

@DisplayName("Тесты создания заказа, связанные с ингредиентами")
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
    public void createOrderWithIngredientsTest(){
        StellarBurgerClient client = new StellarBurgerClient();

        String[] ingredients =  {"61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f"};
        OrderInfoToSend orderInfo = new OrderInfoToSend(ingredients);
        ValidatableResponse response = client.createOrder(orderInfo, authToken);

        response.assertThat().statusCode(SC_CREATED);
        OrderSuccessConfirmation orderSuccessConfirmation = response.extract().as(OrderSuccessConfirmation.class);
        Assertions.assertNotNull(orderSuccessConfirmation);
    }

    @Test
    public void createOrderWithoutIngredientsTest(){
        StellarBurgerClient client = new StellarBurgerClient();

        OrderInfoToSend orderInfo = new OrderInfoToSend();
        ValidatableResponse response = client.createOrder(orderInfo, authToken);

        response.assertThat().statusCode(SC_BAD_REQUEST);
        StandardAnswer answer = response.extract().as(StandardAnswer.class);
        Assertions.assertNotNull(answer);
    }

    @Test
    public void createOrderWithIncorrectIngredientsHashesTest(){
        StellarBurgerClient client = new StellarBurgerClient();

        String[] ingredients =  {"61TEST6d","61TEST6f"};
        OrderInfoToSend orderInfo = new OrderInfoToSend(ingredients);
        ValidatableResponse response = client.createOrder(orderInfo, authToken);

        // Здесь тело ответа не проверяем, т.к. 500 ошибка бывает разная
        response.assertThat().statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
