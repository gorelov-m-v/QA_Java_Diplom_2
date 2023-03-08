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

        stepsUser.changeUserData(newData, stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkAccessTokenNotNull();
        checkRefreshTokenNotNull();
        checkEmail(newData.getEmail());
        checkName(newData.getName());
    }

    @Test
    public void changeUserPasswordWithAuthShouldReturn_200(){
        newData = new User(firstUser.getEmail(), generator.getRANDOM_PASSWORD(), firstUser.getName());

        stepsUser.changeUserData(newData, stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkAccessTokenNotNull();
        checkRefreshTokenNotNull();
        checkEmail(newData.getEmail());
        checkName(newData.getName());
    }

    @Test
    public void changeUserNameWithAuthShouldReturn_200(){
        newData = new User(firstUser.getEmail(), firstUser.getPassword(), generator.getRANDOM_NAME());
        stepsUser.changeUserData(newData, stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkAccessTokenNotNull();
        checkRefreshTokenNotNull();
        checkEmail(newData.getEmail());
        checkName(newData.getName());
    }

    @Test
    public void changeUserEmailWithAuthAndSameEmailShouldReturn_403(){
        secondUser = generator.createRandomUserData();
        stepsUser2.createUser(secondUser);
        stepsUser.changeUserData(new User(firstUser.getEmail(), secondUser.getPassword(), secondUser.getName()), stepsUser2.actualAccessToken);

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

    @Test
    public void changeDeletedUserData() {
        stepsUser.deleteUser(stepsUser.actualAccessToken);
        newData = generator.createRandomUserData();

        stepsUser.changeUserData(newData, stepsUser.actualAccessToken);

        checkStatusCode(403);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getUSER_NOT_FOUND());

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
