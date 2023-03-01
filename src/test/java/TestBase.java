import Constants.Messages;
import Constants.Urls;
import Util.Generator;
import org.junit.Assert;

public class TestBase {

    Steps steps = new Steps();
    Steps steps2 = new Steps();
    User firstUser;
    User secondUser;

    User newData;
    Urls urls = new Urls();
    Generator generator = new Generator();
    Messages messages = new Messages();


    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, steps.ActualStatusCode);
    }

    public void checkSuccessMessage(boolean expected) {
        Assert.assertEquals(expected, steps.ActualSuccessMessage);
    }

    public void checkAccessTokenNotNull() {
        Assert.assertNotNull(steps.ActualAccessToken);
    }

    public void checkErrorMessage(String expected) {
        Assert.assertEquals(expected, steps.ActualErrorMessage);
    }

    public void checkEmail(String expected) { Assert.assertEquals(expected, steps.ActualEmail);}

    public void checkName(String expected) { Assert.assertEquals(expected, steps.ActualName);}

    public void checkBurgerName(String expected) { Assert.assertNotNull(steps.ActualBurgerName);}

    public void checkOrderNumber(int expected) { Assert.assertNotNull(steps.ActualOrderNumber);}

    public void checkOrderNumber() { Assert.assertEquals(steps.ActualOrderNumber, steps.OrderNumber);}
}
