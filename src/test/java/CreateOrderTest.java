import io.restassured.RestAssured;
import org.codehaus.groovy.control.CompilationUnit;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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

//    @Test
//    public void createOrder3() {
//        Ingredients ingredients = new Ingredients(new ArrayList<>());
//        steps.createOrder(ingredients, steps.ActualAccessToken);
//
//        checkStatusCode(400);
//        checkSuccessMessage(false);
//        checkErrorMessage(messages.getINGREDIENTS_MUST_PROVIDED());
//        System.out.println(steps.ActualErrorMessage);
//    }
}

