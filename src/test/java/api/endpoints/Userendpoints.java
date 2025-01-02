package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Userendpoints {

    public static Response createUser(User payload) {
        Response response = given()
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .body(payload)
                        .when()
                            .post(Routes.post_url);
        return response;
    }

    public static Response readUser(String username) {
        Response response = given()
                            .pathParam("username", username) // This inserts the username into {username} placeholder
                        .when()
                            .get(Routes.get_url);  // Ensure you're doing a GET request here, not POST
        return response;
    }

    public static Response updateUser(String username, User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
        .when()
            .put(Routes.update_url);  // Use PUT for updating a resource
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
        .when()
                .delete(Routes.delete_url);  // Use DELETE for deleting a resource
        return response;
    }
}
