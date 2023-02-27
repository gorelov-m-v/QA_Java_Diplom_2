import Constants.Paths;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserSteps {
    Paths paths = new Paths();
    Response response;
    boolean ActualSuccessMessage;
    String ActualAccessToken;
    int ActualStatusCode;
    String ActualErrorMessage;
    String ActualEmail;
    String ActualName;

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
}
