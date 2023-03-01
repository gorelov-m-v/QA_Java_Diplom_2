package Requests;

import Constants.Paths;
import Data.Ingredients;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;

public class StepsOrder {
    Paths paths = new Paths();
    public boolean ActualSuccessMessage;
    public int ActualStatusCode;
    public String ActualErrorMessage;
    public int ActualOrderNumber;
    public String  ActualBurgerName;
    public int OrderNumber;

    public List<String> getIngredientsList() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(paths.getGET_INGREDIENTS())
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
                .post(paths.getCREATE_ORDER())
                .then()
                .extract()
                .response();
        ActualStatusCode = response.getStatusCode();
        if(ActualStatusCode != 500) {
            ActualSuccessMessage = response.path("success");
            if (ActualStatusCode == 400) {
                ActualErrorMessage = response.path("message");
            }
            else if (ActualStatusCode == 200) {
                ActualOrderNumber = response.path("order.number");
                ActualBurgerName = response.path("name");
                OrderNumber = response.path("order.number");
            }
        }
        return response;
    }

    public void getOrder(String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .get(paths.getGET_ORDER())
                .then()
                .extract()
                .response();
        ActualStatusCode = response.getStatusCode();
        ActualSuccessMessage = response.path("success");
        if(ActualStatusCode == 401) {
            ActualErrorMessage = response.path("message");
        }
        else if (ActualStatusCode == 200) {
            OrderNumber = response.path("orders[0].number");
        }
    }
}
