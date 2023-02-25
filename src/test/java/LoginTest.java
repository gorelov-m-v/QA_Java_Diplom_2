import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Constants.*;

public class LoginTest {
    User user = new User().createRandomUserData();
    UserSteps userSteps = new UserSteps();
    Urls urls = new Urls();
    Messages messages = new Messages();
    private String accessToken;
    private String message;

    @Before
    public void setUp() {
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        userSteps.createUser(user);
    }

    @Test
    public void loginWithValidParamsShouldReturn_200() {
        Response response = userSteps.loginUser(user);
        JsonPath jsnPath = response.jsonPath();
        accessToken = jsnPath.get("accessToken");
        message = jsnPath.get("message");

        response.then().statusCode(200);
    }

    @Test
    public void loginWithWrongLoginShouldReturn_401() {
        Response response = userSteps.loginUser(new User("wrongEmail123", user.getPassword(), ""));
        JsonPath jsnPath = response.jsonPath();
        accessToken = jsnPath.get("accessToken");
        message = jsnPath.get("message");

        Assert.assertEquals("Текст ошибки не совпал", messages.getEMAIL_PASSWORD_INCORRECT(), message);
        response.then().statusCode(401);
    }

    @Test
    public void loginWithWrongPasswordShouldReturn_401() {
        Response response = userSteps.loginUser(new User(user.getEmail(), "wrongPassword123", ""));
        JsonPath jsnPath = response.jsonPath();
        accessToken = jsnPath.get("accessToken");
        message = jsnPath.get("message");

        Assert.assertEquals("Текст ошибки не совпал", messages.getEMAIL_PASSWORD_INCORRECT(), message);
        response.then().statusCode(401);
    }

    @After
    public void tearDown() {
        if(accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }
}
