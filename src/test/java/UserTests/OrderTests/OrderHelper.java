package UserTests.OrderTests;

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
        Assert.assertEquals(expected, stepsOrder.actualStatusCode);
    }

    public void checkSuccessMessage(boolean expected) {
        Assert.assertEquals(expected, stepsOrder.actualSuccessMessage);
    }

    public void checkErrorMessage(String expected) {
        Assert.assertEquals(expected, stepsOrder.actualErrorMessage);
    }

    public void checkBurgerName() { Assert.assertNotNull(stepsOrder.actualBurgerName);}

    public void checkOrderNumberNotNull() { Assert.assertNotNull(stepsOrder.actualOrderNumber);}

    public void checkOrderNumber() {
        Assert.assertEquals(stepsOrder.actualOrderNumber, stepsOrder.orderNumber);}
}