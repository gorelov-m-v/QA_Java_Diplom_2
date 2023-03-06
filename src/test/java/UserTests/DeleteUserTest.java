package UserTests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
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
        stepsUser.deleteUser(stepsUser.ActualAccessToken);

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
        stepsUser.deleteUser(stepsUser.ActualAccessToken);

        stepsUser.deleteUser(stepsUser.ActualAccessToken);

        checkStatusCode(404);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_NOT_FOUND());
    }

    @After
    public void tearDown() {
        if(stepsUser.ActualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.ActualAccessToken);
            if(stepsUser2.ActualAccessToken != null) {
                stepsUser.deleteUser(stepsUser2.ActualAccessToken );
            }
        }
    }
}
