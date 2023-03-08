package UserTests;

import Data.Token;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogoutUserTest extends UserHelper{

    @Before
    public void setUp() {
        firstUser = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(firstUser);
        stepsUser.loginUser(firstUser);
    }

    @Test
    public void logoutWithValidToken() {
        token = new Token(stepsUser.actualRefreshToken);
        stepsUser.logoutUser(token);

        checkStatusCode(200);
        checkSuccessMessage(true);
    }

    @Test
    public void logoutWithInvalidToken() {
        token = new Token(generator.getWrongRefreshToken());
        stepsUser.logoutUser(token);

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getTOKEN_REQUIRED());
    }

    @Test
    public void logoutWithEmptyStringToken() {
        token = new Token("");
        stepsUser.logoutUser(token);

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getTOKEN_REQUIRED());
    }

    @Test
    public void logoutWithNullStringToken() {
        token = new Token(null);
        stepsUser.logoutUser(token);

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getTOKEN_REQUIRED());
    }

    @Test
    public void logoutWithoutBody() {
        stepsUser.logoutUser();

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getTOKEN_REQUIRED());
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}
