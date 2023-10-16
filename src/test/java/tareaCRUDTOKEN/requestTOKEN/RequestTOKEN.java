package tareaCRUDTOKEN.requestTOKEN;

import config.Configuration;
import config.ConfigurationTOKEN;
import io.restassured.response.Response;
import tareaCRUDTOKEN.factoryRequestTOKEN.RequestGET;

import static io.restassured.RestAssured.given;

public class RequestTOKEN {

    private static RequestTOKEN requestTOKEN;
    private String token;

    private RequestTOKEN(){
        Response response=given()
                .auth()
                .preemptive()
                .basic(ConfigurationTOKEN.user, ConfigurationTOKEN.password)
                .log()
                .all().
                when()
                .get(ConfigurationTOKEN.authenticationToken);

        token = response.then().extract().path("TokenString");
    }

    public static RequestTOKEN getInstance(){
        if(requestTOKEN==null){
            requestTOKEN=new RequestTOKEN();
        }
        return requestTOKEN;
    }

    public String getToken() {
        return token;
    }
}
