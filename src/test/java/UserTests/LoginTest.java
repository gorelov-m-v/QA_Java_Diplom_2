package UserTests;

import Data.User;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends UserHelper {

    @Before
    public void setUp() {
        firstUser = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(firstUser);
    }

    @Test
    public void loginWithValidParamsShouldReturn_200() {
        stepsUser.loginUser(firstUser);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkAccessTokenNotNull();
        checkRefreshTokenNotNull();
        checkEmail(firstUser.getEmail());
        checkName(firstUser.getName());
    }

    @Test
    public void loginWithWrongLoginShouldReturn_401() {
        stepsUser.loginUser(new User("wrongEmail123", firstUser.getPassword(), ""));

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @Test
    public void loginWithWrongPasswordShouldReturn_401() {
        stepsUser.loginUser(new User(firstUser.getEmail(), "wrongPassword123", ""));

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}
