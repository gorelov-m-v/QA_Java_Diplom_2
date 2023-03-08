package OrderTests;

import Data.Ingredients;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetOrderTest extends OrderHelper {

    @Before
    public void setUp() {
        user = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(user);
        Ingredients ingredients = new Ingredients(generator.getOrderList(stepsOrder.getIngredientsList()));
        stepsOrder.createOrder(ingredients, stepsUser.actualAccessToken);
    }

    @Test
    public void getOrderWithAuth() {
        stepsOrder.getOrder(stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber();
    }

    @Test
    public void getOrderWithoutAuth() {
        stepsOrder.getOrder("");

        checkStatusCode(401);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getYOU_SHOULD_BE_AUTHORISED());
    }

    @Test
    public void getOrderDeletedUser() {
        stepsUser.deleteUser(stepsUser.actualAccessToken);

        stepsOrder.getOrder(stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber();
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}
