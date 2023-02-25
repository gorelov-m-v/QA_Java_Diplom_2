import Constants.Paths;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    Paths paths = new Paths();
    public Response getIngredients() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(paths.getGET_INGREDIENTS());

                return response;
    }

//    public List<Ingredients> getIngredients() {
//        return   given()
//                .header("Content-type", "application/json")
//                .when()
//                .get(paths.getGET_INGREDIENTS())
//                .extract()
//                .path("data._id");

        
//    }

    public Response createOrder(Ingredients ingredients, String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(ingredients)
                .when()
                .post(paths.getCREATE_ORDER());
                return response;
    }
}
