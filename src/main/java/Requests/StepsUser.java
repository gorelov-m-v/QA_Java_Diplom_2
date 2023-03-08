package Requests;

import Constants.Paths;
import Data.Token;
import Data.User;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class StepsUser {
    Paths paths = new Paths();
    Response response;
    public boolean actualSuccessMessage;
    public String actualAccessToken;
    public String actualRefreshToken;
    public int actualStatusCode;
    public String actualErrorMessage;
    public String actualEmail;
    public String actualName;


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
        actualStatusCode = response.getStatusCode();
        actualSuccessMessage = response.path("success");
        if (actualStatusCode == 200) {
            actualRefreshToken = response.path("refreshToken");
            actualAccessToken =  response.path("accessToken");
            actualEmail = response.path("user.email");
            actualName = response.path("user.name");
        } else if(actualStatusCode == 403) {
            actualErrorMessage = response.path("message");
        }
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
        actualStatusCode = response.getStatusCode();
        actualSuccessMessage = response.path("success");
        if (actualStatusCode == 401 || actualStatusCode == 404 || actualStatusCode == 403) {
            actualErrorMessage = response.path("message");
        } else if (actualStatusCode == 200) {
            actualEmail = response.path("user.email");
            actualName = response.path("user.name");
        }
        return response;
    }

    public Response deleteUser(String actualAccessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", actualAccessToken)
                .when()
                .delete(paths.getDELETE_USER_PATH());
        actualStatusCode = response.getStatusCode();
        actualSuccessMessage = response.path("success");
        if(actualStatusCode == 401 || actualStatusCode == 403 || actualStatusCode == 404) {
            actualErrorMessage = response.path("message");
        }
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
        actualStatusCode = response.getStatusCode();
        actualSuccessMessage = response.path("success");
        if (actualStatusCode == 401) {
            actualErrorMessage = response.path("message");
        } else if (actualStatusCode == 200) {
            actualAccessToken = response.path("accessToken");
        }
        return response;
    }

    public Response logoutUser(Token token) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(token)
                .when()
                .post(paths.getLOGOUT_USER_PATH())
                .then()
                .extract()
                .response();
        actualStatusCode = response.getStatusCode();
        actualSuccessMessage = response.path("success");
        return response;
    }
}
