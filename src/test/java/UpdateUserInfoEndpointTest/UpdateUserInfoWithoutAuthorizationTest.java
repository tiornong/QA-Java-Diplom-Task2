package UpdateUserInfoEndpointTest;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.bodytoget.AuthorizationInfo;
import model.bodytoget.StandardAnswer;
import model.bodytoget.UserInfoUpdateConfirmation;
import model.bodytosend.UserToSend;
import org.junit.jupiter.api.*;
import utils.StellarBurgerClient;

import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static utils.Constants.*;
import static utils.Constants.TEST_USER_PASSWORD;


@DisplayName("Тесты изменения информации о пользователе с авторизацией")
public class UpdateUserInfoWithoutAuthorizationTest {
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
        UserInfoUpdateConfirmation userInfo = client.getUserInfo(authToken).extract().as(UserInfoUpdateConfirmation.class);
        UserToSend userChangedInfo = new UserToSend(userInfo.getUser().getEmail(), TEST_USER_PASSWORD, "new-test-name");

        ValidatableResponse response = client.updateUserInfo(userChangedInfo, "");
        response.assertThat().statusCode(SC_UNAUTHORIZED);

        StandardAnswer answer = response.extract().as(StandardAnswer.class);
        Assertions.assertNotNull(answer);
    }

    @Test
    @DisplayName("Обновление емейла пользователя")
    public void updateUserEmailTest(){
        StellarBurgerClient client = new StellarBurgerClient();
        UserInfoUpdateConfirmation userInfo = client.getUserInfo(authToken).extract().as(UserInfoUpdateConfirmation.class);
        UserToSend userChangedInfo = new UserToSend("new-test-name", TEST_USER_PASSWORD, userInfo.getUser().getName());

        ValidatableResponse response = client.updateUserInfo(userChangedInfo, "");
        response.assertThat().statusCode(SC_UNAUTHORIZED);


        StandardAnswer answer = response.extract().as(StandardAnswer.class);
        Assertions.assertNotNull(answer);
    }
}
