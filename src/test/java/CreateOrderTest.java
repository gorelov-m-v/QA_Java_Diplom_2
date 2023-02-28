import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;


public class CreateOrderTest extends TestBase {

    @Before
    public void setUp() {
        firstUser = new User().createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        steps.createUser(firstUser);
    }

    @Test
    public void createOrder() {
        Ingredients ingredients = new Ingredients(generator.getOrderList(steps.getIngredientsList()));
        steps.createOrder(ingredients, steps.ActualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber(steps.ActualOrderNumber);
        checkBurgerName(steps.ActualBurgerName);
    }

    @Test
    public void createOrder2() {
        Ingredients ingredients = new Ingredients(generator.getOrderList(steps.getIngredientsList()));
        steps.createOrder(ingredients, "");

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumber(steps.ActualOrderNumber);
        checkBurgerName(steps.ActualBurgerName);
    }

    @Test
    public void createOrder3() {
        Steps steps1 = new Steps();
        Ingredients ingredients = new Ingredients(new ArrayList<>());
        steps1.createOrder(ingredients, steps.ActualAccessToken);

        System.out.println(steps1.ActualErrorMessage);
        System.out.println(steps1.ActualStatusCode);
        System.out.println(steps1.ActualSuccessMessage);
    }
}

