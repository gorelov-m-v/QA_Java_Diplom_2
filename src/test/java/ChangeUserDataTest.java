import Util.Generator;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Constants.*;

public class ChangeUserDataTest {
    User user = new User().createRandomUserData();
    User newUser = new User().createRandomUserData();
    UserSteps userSteps = new UserSteps();
    Urls urls = new Urls();
    Messages messages = new Messages();
    Generator generator = new Generator();
    private String accessToken;
    private String newUserAccessToken;
    private String actualEmail;
    private String actualName;
    private String message;

    @Before
    public void setUp() {
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        userSteps.createUser(user);
        JsonPath jsnPath = userSteps.loginUser(user).jsonPath();
        accessToken = jsnPath.get("accessToken");
    }

    @Test
    public void changeUserEmailWithAuthShouldReturn_200(){
        User userWithNewData = new User(generator.getRANDOM_EMAIL(), user.getPassword(), user.getName());
        Response response = userSteps.changeUserData(userWithNewData, accessToken);
        JsonPath jsnPath = userSteps.changeUserData(userWithNewData, accessToken).jsonPath();
        actualEmail = jsnPath.get("user.email");

        Assert.assertEquals("Почтовый адрес не совпал", generator.getRANDOM_EMAIL(), actualEmail);
        response.then().statusCode(200);
    }

    @Test
    public void changeUserPasswordWithAuthShouldReturn_200(){
        User userWithNewData = new User(user.getEmail(), generator.getRANDOM_PASSWORD(), user.getName());
        Response response = userSteps.changeUserData(userWithNewData, accessToken);
        response.then().statusCode(200);
    }

    @Test
    public void changeUserNameWithAuthShouldReturn_200(){
        User userWithNewData = new User(user.getEmail(), user.getPassword(), generator.getRANDOM_NAME());
        Response response = userSteps.changeUserData(userWithNewData, accessToken);
        JsonPath jsnPath = userSteps.changeUserData(userWithNewData, accessToken).jsonPath();
        actualName = jsnPath.get("user.name");

        Assert.assertEquals("Почтовый адрес не совпал", generator.getRANDOM_NAME(), actualName);
        response.then().statusCode(200);
    }

    @Test
    public void changeUserEmailWithAuthAndSameEmailShouldReturn_403(){
        userSteps.createUser(newUser);
        JsonPath jsnPath_1 = userSteps.loginUser(newUser).jsonPath();
        newUserAccessToken = jsnPath_1.get("newUserAccessToken");
        Response response = userSteps.changeUserData(newUser, accessToken);
        JsonPath jsnPath_2 = response.jsonPath();
        message = jsnPath_2.get("message");

        Assert.assertEquals("Текст ошибки не совпал", messages.getUSER_EMAIL_ALREADY_EXISTS(), message);
        response.then().statusCode(403);
    }

    @Test
    public void changeUserEmailWithoutAuthShouldReturn_401(){
        User userWithNewData = new User(generator.getRANDOM_EMAIL(), user.getPassword(), user.getName());
        Response response = userSteps.changeUserData(userWithNewData, "");
        JsonPath jsnPath = response.jsonPath();
        message = jsnPath.get("message");

        Assert.assertEquals("Текст ошибки не совпал", messages.getYOU_SHOULD_BE_AUTHORISED(), message);
        response.then().statusCode(401);
    }

    @Test
    public void changeUserPasswordWithoutAuthShouldReturn_401(){
        User userWithNewData = new User(user.getEmail(), generator.getRANDOM_EMAIL(), user.getName());
        Response response = userSteps.changeUserData(userWithNewData, "");
        JsonPath jsnPath = response.jsonPath();
        message = jsnPath.get("message");

        Assert.assertEquals("Текст ошибки не совпал", messages.getYOU_SHOULD_BE_AUTHORISED(), message);
        response.then().statusCode(401);
    }

    @Test
    public void changeUserNameWithoutAuthShouldReturn_401(){
        User userWithNewData = new User(user.getEmail(), user.getPassword(), generator.getRANDOM_NAME());
        Response response = userSteps.changeUserData(userWithNewData, "");
        JsonPath jsnPath = response.jsonPath();
        message = jsnPath.get("message");

        Assert.assertEquals("Текст ошибки не совпал", messages.getYOU_SHOULD_BE_AUTHORISED(), message);
        response.then().statusCode(401);
    }

    @After
    public void tearDown() {
        if(accessToken != null) {
            userSteps.deleteUser(accessToken);
            if(newUserAccessToken != null) {
                userSteps.deleteUser(newUserAccessToken);
            }
        }
    }
}
