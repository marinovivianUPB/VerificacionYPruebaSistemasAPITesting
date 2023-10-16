package tareaCRUDTOKEN.factoryRequestTOKEN;

import config.Configuration;
import tareaCRUDTOKEN.factoryRequestTOKEN.IRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest {
    @Override
    public Response send(RequestInfo requestInfo) {
        Response response=given()
                .header("Token",requestInfo.getHeader("Token"))
                .log()
                .all().
                when()
                .delete(requestInfo.getUrl());
        response.then().log().all();
        return response;
    }
}