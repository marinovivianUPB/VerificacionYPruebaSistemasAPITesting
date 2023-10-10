package basicAPI;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUDItemTest {
    @Test
    public void createUpdateReadDeleteProject() {

        JSONObject bodyItem = new JSONObject();
        int idProject= 4129326;
        bodyItem.put("Content", "holi json");
        bodyItem.put("ProjectId", idProject);

        // create
        Response response=given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com", "12345")
                .body(bodyItem.toString())
                .log()
                .all().
                when()
                .post("https://todo.ly/api/items.json");


        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyItem.get("Content")))
                .body("ProjectId", equalTo(idProject));



        int idItem = response.then().extract().path("Id");

        // update
        bodyItem.put("Checked", "true");
        response=given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com", "12345")
                .body(bodyItem.toString())
                .log()
                .all().
                when()
                .put("https://todo.ly/api/items/"+idItem+".json");


        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyItem.get("Content")))
                .body("Checked", equalTo(true));

        // read
        response=given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com", "12345")
                .log()
                .all().
                when()
                .get("https://todo.ly/api/items/"+idItem+".json");


        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyItem.get("Content")))
                .body("ProjectId", equalTo(idProject))
                .body("Checked", equalTo(true));


        // delete
        response=given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com", "12345")
                .log()
                .all().
                when()
                .delete("https://todo.ly/api/items/"+idItem+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyItem.get("Content")))
                .body("Deleted", equalTo(true));
    }
}
