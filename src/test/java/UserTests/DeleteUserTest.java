package UserTests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeleteUserTest extends UserHelper {

    @Before
    public void setUp() {
        firstUser = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(firstUser);
    }

    @Test
    public void deletionUserWithAuth() {
        stepsUser.deleteUser(stepsUser.actualAccessToken);

        checkStatusCode(202);
        checkSuccessMessage(true);
    }

    @Test
    public void deletionUserWithoutAuth() {
        stepsUser.deleteUser("");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
    }

    @Test
    public void deletionUserWithWrongBearer() {
        stepsUser.deleteUser(generator.getWrongBearerToken());

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getJWT_MALFORMED());
    }
    @Test
    public void deletionUserWithUsedBearer() {
        stepsUser.deleteUser(stepsUser.actualAccessToken);

        stepsUser.deleteUser(stepsUser.actualAccessToken);

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_NOT_FOUND());
    }

    @Test
    public void deletionUserWithInvalidBearer() {
        String invalidToken = stepsUser.actualAccessToken + "f";

        stepsUser.deleteUser(invalidToken);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getINVALID_SIGNATURE());
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
            if(stepsUser2.actualAccessToken != null) {
                stepsUser.deleteUser(stepsUser2.actualAccessToken);
            }
        }
    }
}
