import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Constants.*;

public class LoginTest extends TestBase {

    @Before
    public void setUp() {
        firstUser = new User().createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        userSteps.createUser(firstUser);
    }

    @Test
    public void loginWithValidParamsShouldReturn_200() {
        userSteps.loginUser(firstUser);

        checkSuccessMessage(true);
        checkStatusCode(200);
    }

    @Test
    public void loginWithWrongLoginShouldReturn_401() {
        userSteps.loginUser(new User("wrongEmail123", firstUser.getPassword(), ""));

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getEMAIL_PASSWORD_INCORRECT());
    }

    @Test
    public void loginWithWrongPasswordShouldReturn_401() {
        userSteps.loginUser(new User(firstUser.getEmail(), "wrongPassword123", ""));

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
