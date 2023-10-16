package todoLyTests;

import config.Configuration;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.ConfigurationException;

import static org.hamcrest.Matchers.equalTo;

public class EjercicioClase {

    public String post ="post";
    public String postNoAuth ="postnoauth";

    public RequestInfo requestInfo;
    public Response response;
    @BeforeEach
    public void before(){
        requestInfo = new RequestInfo();
    }
    @Test
    public void createUserCreateProjectCreateItem(){
        JSONObject body = new JSONObject();
        body.put("Email",Configuration.user);
        body.put("FullName","Vivian Marino");
        body.put("Password",Configuration.password);

        //create user
        this.createUser(Configuration.user_host +".json", body, postNoAuth);
        int idUser = response.then().extract().path("Id");

        JSONObject bodyItem = new JSONObject();
        bodyItem.put("Content","holi como estas");

        this.createItem(Configuration.item_host+".json", bodyItem, post);
    }

    private void createUser(String host, JSONObject body, String post) {
        requestInfo.setUrl(host)
                .setBody(body.toString());
        response = FactoryRequest.make(post).send(requestInfo);
        response.then().statusCode(200).
                body("FullName", equalTo(body.get("FullName"))).
                body("Email", equalTo(body.get("Email")));
    }

    private void createItem(String host, JSONObject body, String post) {
        requestInfo.setUrl(host)
                .setBody(body.toString());
        response = FactoryRequest.make(post).send(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
    }
}
