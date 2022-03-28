package souress.lab_web4.data;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/data")
public class DataPath {
    @EJB
    private DataBean dataBean;

    @POST
    @Path("/submit")
    public Response submit(String json) {
        Response.ResponseBuilder responseBuilder = Response.ok();
        responseBuilder.header("Content-Type", "application/json");
        DataStatus dataStatus = dataBean.submit(json);
        if (dataStatus == DataStatus.OK) {
            responseBuilder.status(200);
            responseBuilder.entity("{\"status\": \"true\"}");
        return responseBuilder.build();
        } else return dataBean.handleError(dataStatus, responseBuilder);
    }
}
