package UserTests;

import Data.User;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends UserHelper {

    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
    }

    @Test
    public void creatingUserWithValidParamShouldReturn_200() {
        User validUserData = generator.createRandomUserData();
        stepsUser.createUser(validUserData);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkAccessTokenNotNull();
        checkRefreshTokenNotNull();
        checkEmail(validUserData.getEmail());
        checkName(validUserData.getName());
    }

    @Test
    public void creatingUserWithSameDataShouldReturn_403() {
        User validUserData = generator.createRandomUserData();
        stepsUser.createUser(validUserData);
        stepsUser.createUser(validUserData);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_ALREADY_EXISTS());
    }

    @Test
    public void creatingUserWithoutNameShouldReturn_403() {
        User userDataWithoutName = generator.createUserWithoutName();
        stepsUser.createUser(userDataWithoutName);

        checkStatusCode(403);
        checkErrorMessage(messages.getCREATING_USER_WITHOUT_FIELD());
        checkSuccessMessage(false);
    }

    @Test
    public void creatingUserWithoutEmailShouldReturn_403() {
        User userDataWithoutEmail = generator.createUserWithoutEmail();
        stepsUser.createUser(userDataWithoutEmail);

        checkStatusCode(403);
        checkErrorMessage(messages.getCREATING_USER_WITHOUT_FIELD());
        checkSuccessMessage(false);
    }

    @Test
    public void creatingUserWithoutPasswordShouldReturn_403() {
        User userDataWithoutPassword = generator.createUserWithoutPassword();
        stepsUser.createUser(userDataWithoutPassword);

        checkStatusCode(403);
        checkErrorMessage(messages.getCREATING_USER_WITHOUT_FIELD());
        checkSuccessMessage(false);
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}


