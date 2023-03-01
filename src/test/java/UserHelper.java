import Constants.Messages;
import Constants.Urls;
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

    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, stepsUser.ActualStatusCode);
    }

    public void checkSuccessMessage(boolean expected) {
        Assert.assertEquals(expected, stepsUser.ActualSuccessMessage);
    }

    public void checkAccessTokenNotNull() {
        Assert.assertNotNull(stepsUser.ActualAccessToken);
    }

    public void checkErrorMessage(String expected) {
        Assert.assertEquals(expected, stepsUser.ActualErrorMessage);
    }

    public void checkEmail(String expected) { Assert.assertEquals(expected, stepsUser.ActualEmail);}

    public void checkName(String expected) { Assert.assertEquals(expected, stepsUser.ActualName);}

}