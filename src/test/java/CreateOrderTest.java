import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class CreateOrderTest extends OrderHelper {

    @Before
    public void setUp() {
        user = new User().createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(user);
    }

    @Test
    public void createOrder() {
        Ingredients ingredients = new Ingredients(generator.getOrderList(stepsOrder.getIngredientsList()));
        stepsOrder.createOrder(ingredients, stepsUser.ActualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber(stepsOrder.ActualOrderNumber);
        checkBurgerName(stepsOrder.ActualBurgerName);
    }

    @Test
    public void createOrder2() {
        Ingredients ingredients = new Ingredients(generator.getOrderList(stepsOrder.getIngredientsList()));
        stepsOrder.createOrder(ingredients, "");

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber(stepsOrder.ActualOrderNumber);
        checkBurgerName(stepsOrder.ActualBurgerName);
    }

    @Test
    public void createOrder3() {
        Ingredients ingredients = new Ingredients(new ArrayList<>());
        stepsOrder.createOrder(ingredients, stepsUser.ActualAccessToken);

        checkStatusCode(400);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getINGREDIENTS_MUST_PROVIDED());
    }

    @Test
    public void createOrder4() {
        Ingredients ingredients = new Ingredients(generator.getRandomList(3));
        stepsOrder.createOrder(ingredients, stepsUser.ActualAccessToken);

        checkStatusCode(500);
    }
}

