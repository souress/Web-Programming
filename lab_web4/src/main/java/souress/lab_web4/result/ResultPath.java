package souress.lab_web4.result;

import org.json.JSONObject;
import souress.lab_web4.auth.AuthBean;
import souress.lab_web4.auth.AuthStatus;
import souress.lab_web4.database.dao.ShotDao;
import souress.lab_web4.entities.Shot;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/result")
public class ResultPath {
    @EJB
    private ShotDao shotDao;

    @POST
    @Path("/shots")
    public Response getShots(String json) {
        Response.ResponseBuilder responseBuilder = Response.ok();
        responseBuilder.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject(json);
        List<Shot> shotList = shotDao.getShots(jsonObject.getString("login"));
        System.out.println("Длина массива: " + shotList.toArray().length);
        if (!shotList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('[');
            for (Shot shot : shotList) {
                stringBuilder.append(shot.toString());
                stringBuilder.append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(']');
            responseBuilder.status(200);
            responseBuilder.entity("{\"data\": " + stringBuilder + ", \"status\": \"true\"}");
        } else {
            responseBuilder.entity(String.format("{\"data\": \"%s\", \"status\": \"%b\"}", ResultStatus.EMPTY.getDescription(), false));
            responseBuilder.status(500);
        }
        return responseBuilder.build();
    }

    @POST
    @Path("/clear")
    public Response clearTable(String json) {
        Response.ResponseBuilder responseBuilder = Response.ok();
        responseBuilder.header("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject(json);
        shotDao.clearShotsTable(jsonObject.getString("login"));
        System.out.println("очистка таблицы...");
        responseBuilder.status(200);
        responseBuilder.entity("{\"status\": \"true\"}");
        return responseBuilder.build();
    }
}
