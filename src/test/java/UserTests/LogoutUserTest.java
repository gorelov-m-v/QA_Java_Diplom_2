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
    public void logoutSmokeTest() {
        token = new Token(stepsUser.actualRefreshToken);
        stepsUser.logoutUser(token);

        checkStatusCode(200);
        checkSuccessMessage(true);
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}
