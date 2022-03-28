package souress.lab_web4.data;

import org.json.JSONObject;
import souress.lab_web4.database.dao.ShotDao;
import souress.lab_web4.entities.Shot;
import souress.lab_web4.util.Validation;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static souress.lab_web4.util.AreaCheck.isInArea;

@Stateful
public class DataBean {
    @EJB
    private ShotDao shotDao;

    private double x, y, r;

    public DataBean() {
        x = 0;
        y = 0;
        r = 0;
    }

    public DataStatus submit(String json) {
        DataStatus status;
        JSONObject object = new JSONObject(json);
        this.x = object.getDouble("x");
        this.y = object.getDouble("y");
        this.r = object.getDouble("r");
        String userLogin = object.getString("login");
        if (Validation.checkAll(x, y, r)) {
            status = DataStatus.OK;
            long start = System.nanoTime();
            Shot shot = new Shot();
            shot.setX(x);
            shot.setY(y);
            shot.setR(r);
            shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            shot.setDuration((System.nanoTime() - start) / 1000);
            shot.setResult(isInArea(x, y, r));
            shot.setOwner(userLogin);
            shotDao.addShot(shot);
        } else status = DataStatus.REQUEST_ERROR;
        return status;
    }

    public Response handleError(DataStatus status, Response.ResponseBuilder responseBuilder) {
        responseBuilder.entity(String.format("{\"data\": \"%s\", \"status\": \"%b\"}", status.getDescription(), false));
        switch (status) {
            case REQUEST_ERROR:
                responseBuilder.status(500);
                break;
            case UNDEFINED_ERROR:
                responseBuilder.status(520);
                break;
        }
        return responseBuilder.build();
    }
}
