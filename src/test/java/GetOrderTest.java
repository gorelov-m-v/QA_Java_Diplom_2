import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetOrderTest extends TestBase {

    @Before
    public void setUp() {
        firstUser = new User().createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        steps.createUser(firstUser);
        Ingredients ingredients = new Ingredients(generator.getOrderList(steps.getIngredientsList()));
        steps.createOrder(ingredients, steps.ActualAccessToken);
    }

    @Test
    public void getOrder1() {
        steps.getOrder(steps.ActualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber();
    }

    @Test
    public void getOrder2() {
        steps.getOrder("");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
    }

    @After
    public void tearDown() {
        if(steps.ActualAccessToken != null) {
            steps.deleteUser(steps.ActualAccessToken);
        }
    }
}
