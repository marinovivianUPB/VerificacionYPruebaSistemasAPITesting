package basicAPI;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicRestAssured {
    @Test
    public void createProjectByApi(){
        given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com","12345")
                .body("{\n" +
                        " \"Content\":\"Vivian RESTASSURED\",\n" +
                        " \"Icon\":2 \n" +
                        "}\n")
                .log()
                .all().
                when()
                .post("https://todo.ly/api/projects.json").
                then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo("Vivian RESTASSURED"))
                .body("Icon",equalTo(2));
    }

    @Test
    public void createProjectByApiWithJsonObject(){
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content","Vivian json");
        bodyProject.put("Icon",2);


        Response response = given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com","12345")
                .body(bodyProject.toString())
                .log()
                .all().
                when()
                .post("https://todo.ly/api/projects.json");

                response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content",equalTo(bodyProject.get("Content")))
                .body("Icon",equalTo(2));

        int idProject = response.then().extract().path("Id");

        given()
                .auth()
                .preemptive()
                .basic("upbapi@upbapi.com","12345").log()
                .all().when().delete();
    }
}
