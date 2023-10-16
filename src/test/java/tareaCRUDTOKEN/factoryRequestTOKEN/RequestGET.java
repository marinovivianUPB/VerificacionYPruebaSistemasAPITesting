package tareaCRUDTOKEN.factoryRequestTOKEN;

import tareaCRUDTOKEN.factoryRequestTOKEN.IRequest;
import factoryRequest.RequestInfo;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest {
    @Override
    public Response send(RequestInfo requestInfo) {
        Response response=given()
                .header("Token",requestInfo.getHeader("Token"))
                .log()
                .all().
                when()
                .get(requestInfo.getUrl());
        response.then().log().all();
        return response;
    }
}