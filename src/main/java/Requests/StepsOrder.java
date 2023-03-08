package Requests;

import Constants.Paths;
import Data.Ingredients;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;

public class StepsOrder {
    Paths paths = new Paths();
    public boolean actualSuccessMessage;
    public int actualStatusCode;
    public String actualErrorMessage;
    public int createdOrderNumber;
    public String actualBurgerName;
    public int receivedOrderNumber;
    public List<String> receivedOrderList;

    public List<String> getIngredientsList() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(paths.getGET_INGREDIENTS_PATH())
                .then()
                .extract()
                .response();
        List<String> ingredients = response.path("data._id");
        return ingredients;
    }

    public Response createOrder(Ingredients ingredients, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(ingredients)
                .when()
                .post(paths.getCREATE_ORDER_PATH())
                .then()
                .extract()
                .response();
        actualStatusCode = response.getStatusCode();
        if(actualStatusCode != 500) {
            actualSuccessMessage = response.path("success");
            if (actualStatusCode == 400) {
                actualErrorMessage = response.path("message");
            }
            else if (actualStatusCode == 200) {
                actualBurgerName = response.path("name");
                createdOrderNumber = response.path("order.number");
            }
        }
        return response;
    }

    public Response getOrder(String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .get(paths.getGET_ORDER_PATH())
                .then()
                .extract()
                .response();
        actualStatusCode = response.getStatusCode();
        actualSuccessMessage = response.path("success");
        if(actualStatusCode == 401) {
            actualErrorMessage = response.path("message");
        }
        else if (actualStatusCode == 200) {
            receivedOrderNumber = response.path("orders[0].number");
            receivedOrderList = response.path("orders[0].ingredients");
        }
        return response;
    }
}
