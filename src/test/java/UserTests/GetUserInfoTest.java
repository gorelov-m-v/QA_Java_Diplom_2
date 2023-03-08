package UserTests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetUserInfoTest extends UserHelper {

    @Before
    public void setUp() {
        firstUser = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(firstUser);
    }

    @Test
    public void getUserDataWithAuth() {
        stepsUser.getUserData(stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkEmail(firstUser.getEmail());
        checkName(firstUser.getName());
    }

    @Test
    public void getUserDataWithInvalidBearerToken() {
        String invalidToken = generator.getInvalidBearerToken(stepsUser.actualAccessToken);

        stepsUser.getUserData(invalidToken);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getINVALID_SIGNATURE());
    }

    @Test
    public void getUserDataWithWrongBearerToken() {
        String wrongBearerToken = generator.getWrongBearerToken();

        stepsUser.getUserData(wrongBearerToken);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getJWT_MALFORMED());
    }

    @Test
    public void getUserDataWithoutAuth() {
        stepsUser.getUserData("");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
    }

    @Test
    public void getDeletedUserData() {
        stepsUser.deleteUser(stepsUser.actualAccessToken);

        stepsUser.getUserData(stepsUser.actualAccessToken);

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_NOT_FOUND());
    }

    @After
    public void tearDown() {
        if (stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}
