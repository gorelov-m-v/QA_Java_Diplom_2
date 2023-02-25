import Util.Generator;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Constants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CreateOrderTest {
    private User user = new User().createRandomUserData();
    private List<String> ingredients = new ArrayList<>();
    private List<String> orderIngredients = new ArrayList<>();
    UserSteps userSteps = new UserSteps();
    OrderSteps orderSteps = new OrderSteps();
    private String accessToken;
    Urls urls = new Urls();
    Faker faker = new Faker();

    @Test
    public void createOrder() {
        RestAssured.baseURI = urls.getSTELLAR_BURGERS_PROD();
        Response response = userSteps.createUser(user);
        JsonPath jsonPath = response.jsonPath();
        accessToken = jsonPath.get("accessToken");
        Response response1 = orderSteps.getIngredients();
        JsonPath jsonPath_1 = response1.jsonPath();

        ingredients = jsonPath_1.get("data._id");
//        ingredients = orderSteps.getIngredients().extract().path("data._id");
        System.out.println(ingredients.toArray().length);
        System.out.println(ingredients);
        int randomNumber = faker.number().numberBetween(1,ingredients.toArray().length);

        for(int i = 0; i < randomNumber; i++) {
            orderIngredients.add(ingredients.toArray()[i].toString());
        }





//        Ingredients orderIngredients = new Ingredients(ingredients.get(1));
//        Response response_2 = orderSteps.createOrder(orderIngredients, accessToken);
//        System.out.println(orderIngredients);
    }

}
