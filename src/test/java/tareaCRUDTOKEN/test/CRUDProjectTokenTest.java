package tareaCRUDTOKEN.test;

import config.Configuration;
import config.ConfigurationTOKEN;
import tareaCRUDTOKEN.factoryRequestTOKEN.FactoryRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tareaCRUDTOKEN.requestTOKEN.RequestTOKEN;

import static org.hamcrest.Matchers.equalTo;

public class CRUDProjectTokenTest {

    public String post ="post";
    public String put = "put";
    public String get ="get";
    public String delete ="delete";

    public RequestInfo requestInfo;
    public Response response;
    @BeforeEach
    public void before(){
        requestInfo = new RequestInfo();
        requestInfo.setHeaders("Token",RequestTOKEN.getInstance().getToken());
    }

    @Test
    public void createReadUpdateDeleteProject(){
        JSONObject body = new JSONObject();
        body.put("Content","TareaAPI");

        this.createProject(body, post);
        int idProject = response.then().extract().path("Id");
        this.readProject(idProject, get, body);
        body.put("Content","TareaAPIUpdated");
        this.updateProject(idProject, body, put);
        this.deleteProject(idProject, delete, body);
    }

    private void createProject(JSONObject body, String post) {
        requestInfo.setUrl(ConfigurationTOKEN.project_host + ".json")
                .setBody(body.toString());
        response = FactoryRequest.make(post).send(requestInfo);
        response.then().statusCode(200)
                .body("Content", equalTo(body.get("Content")));
    }

    private void deleteProject(int idProject, String delete, JSONObject body) {
        requestInfo.setUrl(ConfigurationTOKEN.project_host + "/" + idProject + ".json");
        response = FactoryRequest.make(delete).send(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")))
                .body("Deleted", equalTo(true));
    }

    private void updateProject(int idProject, JSONObject body, String put) {
        requestInfo.setUrl(ConfigurationTOKEN.project_host + "/" + idProject + ".json")
                .setBody(body.toString());
        response = FactoryRequest.make(put).send(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
    }

    private void readProject(int idProject, String get, JSONObject body) {
        requestInfo.setUrl(ConfigurationTOKEN.project_host + "/" + idProject + ".json");
        response = FactoryRequest.make(get).send(requestInfo);
        response.then().statusCode(200).
                body("Id", equalTo(idProject));
    }


}
