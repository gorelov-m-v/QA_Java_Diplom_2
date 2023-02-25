import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Constants.*;


public class CreateUserTest {
    private String message;
    private boolean success;
    private String accessToken;

    User user = new User().createRandomUserData();
    UserSteps userSteps = new UserSteps();
    Messages messages = new Messages();
    Urls urls = new Urls();

    @Before
    public void setUp(){
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
    }

    @Test
    public void creatingUserWithValidParamShouldReturn_200() {
        Response response = userSteps.createUser(user);
        JsonPath jsnPath = response.jsonPath();
        success =  jsnPath.get("success");
        accessToken = jsnPath.get("accessToken");

        Assert.assertEquals("Success не совпал", true, success);
        response.then().statusCode(200);
    }

    @Test
    public void creatingUserWithSameDataShouldReturn_403() {
        Response responseFirst = userSteps.createUser(user);
        Response responseSecond = userSteps.createUser(user);
        responseFirst.then().statusCode(200);
        JsonPath jsnPath = responseSecond.jsonPath();
        message = jsnPath.get("message");
        success = jsnPath.get("success");
        accessToken = jsnPath.get("accessToken");

        Assert.assertEquals("Success не совпал", false, success);
        Assert.assertEquals("Текст ошибки не совпал", messages.getUSER_ALREADY_EXISTS(), message);
        responseSecond.then().statusCode(403);
    }

    @Test
    public void creatingUserWithoutNameShouldReturn_403() {
        Response response = userSteps.createUser(new User(user.getEmail(), user.getPassword(), ""));
        JsonPath jsnPath = response.jsonPath();
        message = jsnPath.get("message");
        success =  jsnPath.get("success");
        accessToken = jsnPath.get("accessToken");

        Assert.assertEquals("Success не совпал", false, success);
        Assert.assertEquals("Текст ошибки не совпал", messages.getCREATING_USER_WITHOUT_FIELD(), message);
        response.then().statusCode(403);
    }

    @Test
    public void creatingUserWithoutEmailShouldReturn_403() {
        Response response = userSteps.createUser(new User("", user.getPassword(), user.getName()));
        JsonPath jsnPath = response.jsonPath();
        message = jsnPath.get("message");
        success =  jsnPath.get("success");
        accessToken = jsnPath.get("accessToken");

        Assert.assertEquals("Success не совпал", false, success);
        Assert.assertEquals("Текст ошибки не совпал", messages.getCREATING_USER_WITHOUT_FIELD(), message);
        response.then().statusCode(403);
    }

    @Test
    public void creatingUserWithoutPasswordShouldReturn_403() {
        Response response = userSteps.createUser(new User(user.getEmail(), "", user.getName()));
        JsonPath jsnPath = response.jsonPath();
        message = jsnPath.get("message");
        success =  jsnPath.get("success");
        accessToken = jsnPath.get("accessToken");

        Assert.assertEquals("Success не совпал", false, success);
        Assert.assertEquals("Текст ошибки не совпал", messages.getCREATING_USER_WITHOUT_FIELD(), message);
        response.then().statusCode(403);
    }

    @After
    public void tearDown() {
        if(accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

}


