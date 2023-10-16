package tareaCRUDTOKEN.factoryRequestTOKEN;

import factoryRequest.RequestInfo;
import io.restassured.response.Response;

public interface IRequest {
    Response send (RequestInfo requestInfo);
}