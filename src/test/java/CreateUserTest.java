import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Constants.*;


public class CreateUserTest extends TestBase {

    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
    }

    @Test
    public void creatingUserWithValidParamShouldReturn_200() {
        User validUserData = new User().createRandomUserData();
        userSteps.createUser(validUserData);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkAccessTokenNotNull();
    }

    @Test
    public void creatingUserWithSameDataShouldReturn_403() {
        User validUserData = new User().createRandomUserData();
        userSteps.createUser(validUserData);
        userSteps.createUser(validUserData);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_ALREADY_EXISTS());
    }

    @Test
    public void creatingUserWithoutNameShouldReturn_403() {
        User userDataWithoutName = new User().createUserWithoutName();
        userSteps.createUser(userDataWithoutName);

        checkStatusCode(403);
        checkErrorMessage(messages.getCREATING_USER_WITHOUT_FIELD());
        checkSuccessMessage(false);
    }

    @Test
    public void creatingUserWithoutEmailShouldReturn_403() {
        User userDataWithoutEmail = new User().createUserWithoutEmail();
        userSteps.createUser(userDataWithoutEmail);

        checkStatusCode(403);
        checkErrorMessage(messages.getCREATING_USER_WITHOUT_FIELD());
        checkSuccessMessage(false);
    }

    @Test
    public void creatingUserWithoutPasswordShouldReturn_403() {
        User userDataWithoutPassword = new User().createUserWithoutPassword();
        userSteps.createUser(userDataWithoutPassword);

        checkStatusCode(403);
        checkErrorMessage(messages.getCREATING_USER_WITHOUT_FIELD());
        checkSuccessMessage(false);
    }

    @After
    public void tearDown() {
        if(userSteps.ActualAccessToken != null) {
            userSteps.deleteUser(userSteps.ActualAccessToken);
        }
    }

}


