package UserTests;

import Constants.Messages;
import Constants.Urls;
import Data.Token;
import Data.User;
import Requests.StepsUser;
import Util.Generator;
import org.junit.Assert;

public class UserHelper {

    StepsUser stepsUser = new StepsUser();
    StepsUser stepsUser2 = new StepsUser();
    Urls urls = new Urls();
    Generator generator = new Generator();
    Messages messages = new Messages();
    User firstUser;
    User secondUser;
    User newData;
    Token token;

    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, stepsUser.actualStatusCode);
    }

    public void checkSuccessMessage(boolean expected) {
        Assert.assertEquals(expected, stepsUser.actualSuccessMessage);
    }

    public void checkAccessTokenNotNull() {
        Assert.assertNotNull(stepsUser.actualAccessToken);
    }

    public void checkRefreshTokenNotNull() { Assert.assertNotNull(stepsUser.actualRefreshToken);}

    public void checkErrorMessage(String expected) {
        Assert.assertEquals(expected, stepsUser.actualErrorMessage);
    }

    public void checkEmail(String expected) { Assert.assertEquals(expected, stepsUser.actualEmail);}

    public void checkName(String expected) { Assert.assertEquals(expected, stepsUser.actualName);}
}
