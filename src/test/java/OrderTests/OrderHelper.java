package OrderTests;

import Constants.Messages;
import Constants.Urls;
import Data.User;
import Requests.StepsOrder;
import Requests.StepsUser;
import Util.Generator;
import org.junit.Assert;

public class OrderHelper {

    StepsUser stepsUser = new StepsUser();
    Urls urls = new Urls();
    Generator generator = new Generator();
    Messages messages = new Messages();

    StepsOrder stepsOrder = new StepsOrder();
    User user;

    public void checkStatusCode(int expected) {
        Assert.assertEquals(expected, stepsOrder.ActualStatusCode);
    }

    public void checkSuccessMessage(boolean expected) {
        Assert.assertEquals(expected, stepsOrder.ActualSuccessMessage);
    }

    public void checkErrorMessage(String expected) {
        Assert.assertEquals(expected, stepsOrder.ActualErrorMessage);
    }

    public void checkBurgerName(String expected) { Assert.assertNotNull(stepsOrder.ActualBurgerName);}

    public void checkOrderNumber(int expected) { Assert.assertNotNull(stepsOrder.ActualOrderNumber);}

    public void checkOrderNumber() {
        Assert.assertEquals(stepsOrder.ActualOrderNumber, stepsOrder.OrderNumber);}
}