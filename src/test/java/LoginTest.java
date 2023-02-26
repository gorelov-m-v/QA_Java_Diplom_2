import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Constants.*;

public class LoginTest extends TestBase {
    User user;
    Urls urls = new Urls();
    Messages messages = new Messages();


    @Before
    public void setUp() {
        user = new User().createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        userSteps.createUser(user);
    }

    @Test
    public void loginWithValidParamsShouldReturn_200() {
        userSteps.loginUser(user);

        checkSuccessMessage(true);
        checkStatusCode(200);
    }

    @Test
    public void loginWithWrongLoginShouldReturn_401() {
        userSteps.loginUser(new User("wrongEmail123", user.getPassword(), ""));

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getEMAIL_PASSWORD_INCORRECT());
    }
//
    @Test
    public void loginWithWrongPasswordShouldReturn_401() {
        userSteps.loginUser(new User(user.getEmail(), "wrongPassword123", ""));

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @After
    public void tearDown() {
        if(userSteps.ActualAccessToken != null) {
            userSteps.deleteUser(userSteps.ActualAccessToken);
        }
    }
}
