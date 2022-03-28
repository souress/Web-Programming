package souress.lab_web4.auth;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;

@Path("/auth")
public class AuthPath {

    @EJB
    private AuthBean authBean;

    @POST
    @Path("/sign-in")
    public Response signIn(String jsonStr) throws NoSuchAlgorithmException {
        Response.ResponseBuilder responseBuilder = Response.ok();
        responseBuilder.header("Content-Type", "application/json");

        AuthStatus status = authBean.signIn(jsonStr);
        if (status == AuthStatus.OK) {
            responseBuilder.status(200);
            responseBuilder.entity("{\"status\": \"true\"}");
            return responseBuilder.build();
        }
        return authBean.handleError(status, responseBuilder);
    }

    @POST
    @Path("/sign-up")
    public Response signUp(String jsonStr) throws NoSuchAlgorithmException {
        Response.ResponseBuilder responseBuilder = Response.ok();
        responseBuilder.header("Content-Type", "application/json");

        AuthStatus status = authBean.signUp(jsonStr);
        if (status == AuthStatus.OK) {
            responseBuilder.status(200);
            responseBuilder.entity("{\"status\": \"true\"}");
            return responseBuilder.build();
        }
        return authBean.handleError(status, responseBuilder);
    }
}
