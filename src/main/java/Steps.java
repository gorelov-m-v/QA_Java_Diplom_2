import Constants.Paths;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps {
    Paths paths = new Paths();
    Response response;
    boolean ActualSuccessMessage;
    String ActualAccessToken;
    int ActualStatusCode;
    String ActualErrorMessage;
    String ActualEmail;
    String ActualName;
    int ActualOrderNumber;
    String  ActualBurgerName;

    public Response createUser(User user) {
        response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(paths.getCREATE_USER_PATH())
                .then()
                .extract()
                .response();
        ActualSuccessMessage = response.path("success");
        ActualAccessToken =  response.path("accessToken");
        ActualStatusCode = response.getStatusCode();
        ActualErrorMessage = response.path("message");
        return response;
    }

    public Response deleteUser(String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when()
                .delete(paths.getDELETE_USER_PATH());
        response.then().statusCode(202);
        return response;
    }

    public Response loginUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(paths.getLOGIN_USER_PATH())
                .then()
                .extract()
                .response();
        ActualSuccessMessage = response.path("success");
        ActualAccessToken =  response.path("accessToken");
        ActualStatusCode = response.getStatusCode();
        ActualErrorMessage = response.path("message");
        return response;
    }

    public Response changeUserData(User userWithNewData, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(userWithNewData)
                .when()
                .patch(paths.getCHANGE_USER_DATA_PATH())
                .then()
                .extract()
                .response();
        ActualSuccessMessage = response.path("success");
        ActualAccessToken =  response.path("accessToken");
        ActualStatusCode = response.getStatusCode();
        ActualErrorMessage = response.path("message");
        ActualEmail = response.path("user.email");
        ActualName = response.path("user.name");
        return response;
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
        ActualSuccessMessage = response.path("success");
        if(ActualStatusCode == 200) {
        ActualOrderNumber = response.path("order.number");
        ActualBurgerName = response.path("name");
        }
        return response;
    }

    public List<String> getIngredientsList() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(paths.getGET_INGREDIENTS())
                .then()
                .extract()
                .response();
        List<String> ingredients = response.path("data._id");
//        String[] array = new String[ingredients.size()];
//        for(int i = 0; i < ingredients.size(); i++) {
//            array[i] = ingredients.get(i);
//        }
        return ingredients;
    }
}
