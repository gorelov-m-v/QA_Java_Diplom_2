import org.junit.Assert;

public class TestBase {

    UserSteps userSteps = new UserSteps();

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
}
