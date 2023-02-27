import Constants.Messages;
import Constants.Urls;
import Util.Generator;
import org.junit.Assert;

public class TestBase {

    UserSteps userSteps = new UserSteps();
    UserSteps userSteps2 = new UserSteps();
    User firstUser;
    User secondUser;

    User newData;
    Urls urls = new Urls();
    Generator generator = new Generator();
    Messages messages = new Messages();


    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, userSteps.ActualStatusCode);
    }

    public void checkSuccessMessage(boolean expected) {
        Assert.assertEquals(expected,userSteps.ActualSuccessMessage);
    }

    public void checkAccessTokenNotNull() {
        Assert.assertNotNull(userSteps.ActualAccessToken);
    }

    public void checkErrorMessage(String expected) {
        Assert.assertEquals(expected, userSteps.ActualErrorMessage);
    }

    public void checkEmail(String expected) { Assert.assertEquals(expected, userSteps.ActualEmail);}

    public void checkName(String expected) { Assert.assertEquals(expected, userSteps.ActualName);}
}
