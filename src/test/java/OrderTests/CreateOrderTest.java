package OrderTests;

import Data.Ingredients;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class CreateOrderTest extends OrderHelper {

    @Before
    public void setUp() {
        user = generator.createRandomUserData();
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        stepsUser.createUser(user);
    }

    @Test
    public void createOrderWithAuthAndValidData() {
        Ingredients ingredients = new Ingredients(generator.getOrderList(stepsOrder.getIngredientsList()));
        stepsOrder.createOrder(ingredients, stepsUser.actualAccessToken);

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumberNotNull();
        checkBurgerName();
    }

    @Test
    public void createOrderWithoutAuth() {
        Ingredients ingredients = new Ingredients(generator.getOrderList(stepsOrder.getIngredientsList()));
        stepsOrder.createOrder(ingredients, "");

        checkStatusCode(200);
        checkSuccessMessage(true);
        checkOrderNumberNotNull();
        checkBurgerName();
    }

    @Test
    public void createOrderWithoutData() {
        Ingredients ingredients = new Ingredients(new ArrayList<>());
        stepsOrder.createOrder(ingredients, stepsUser.actualAccessToken);

        checkStatusCode(400);
        checkSuccessMessage(false);
        checkErrorMessage(messages.getINGREDIENTS_MUST_PROVIDED());
    }

    @Test
    public void createOrderWithWrongHash() {
        Ingredients ingredients = new Ingredients(generator.getRandomOrderList(3));
        stepsOrder.createOrder(ingredients, stepsUser.actualAccessToken);

        checkStatusCode(500);
    }

    @Test
    public void createOrderByDeletedUser() {
        stepsUser.deleteUser(stepsUser.actualAccessToken);
        Ingredients ingredients = new Ingredients(generator.getRandomOrderList(3));

        stepsOrder.createOrder(ingredients, stepsUser.actualAccessToken);

        checkStatusCode(500);
    }

    @After
    public void tearDown() {
        if(stepsUser.actualAccessToken != null) {
            stepsUser.deleteUser(stepsUser.actualAccessToken);
        }
    }
}

