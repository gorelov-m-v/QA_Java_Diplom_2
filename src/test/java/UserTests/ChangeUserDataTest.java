package UserTests;

import Data.User;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChangeUserDataTest extends UserHelper {

    @Before
    public void setUp() {
        firstUser = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(firstUser);
    }

    @Test
    public void changeUserEmailWithAuthShouldReturn_200(){
        newData = new User(generator.getRANDOM_EMAIL(), firstUser.getPassword(), firstUser.getName());
        stepsUser.changeUserData(newData, stepsUser.ActualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkEmail(generator.getRANDOM_EMAIL());
    }

    @Test
    public void changeUserPasswordWithAuthShouldReturn_200(){
        newData = new User(firstUser.getEmail(), generator.getRANDOM_PASSWORD(), firstUser.getName());
        stepsUser.changeUserData(newData, stepsUser.ActualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
    }

    @Test
    public void changeUserNameWithAuthShouldReturn_200(){
        newData = new User(firstUser.getEmail(), firstUser.getPassword(), generator.getRANDOM_NAME());
        stepsUser.changeUserData(newData, stepsUser.ActualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkName(generator.getRANDOM_NAME());
    }

    @Test
    public void changeUserEmailWithAuthAndSameEmailShouldReturn_403(){
        secondUser = generator.createRandomUserData();
        stepsUser2.createUser(secondUser);
        stepsUser.changeUserData(new User(firstUser.getEmail(), secondUser.getPassword(), secondUser.getName()), stepsUser2.ActualAccessToken);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_EMAIL_ALREADY_EXISTS());
    }

    @Test
    public void changeUserEmailWithoutAuthShouldReturn_401(){
        newData = new User(generator.getRANDOM_EMAIL(), firstUser.getPassword(), firstUser.getName());
        stepsUser.changeUserData(newData, "");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
    }

    @Test
    public void changeUserPasswordWithoutAuthShouldReturn_401(){
        newData = new User(firstUser.getEmail(), generator.getRANDOM_PASSWORD(), firstUser.getName());
        stepsUser.changeUserData(newData, "");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
    }

    @Test
    public void changeUserNameWithoutAuthShouldReturn_401(){
        newData = new User(firstUser.getEmail(), firstUser.getPassword(), generator.getRANDOM_NAME());
        stepsUser.changeUserData(newData, "");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
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
