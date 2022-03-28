package souress.lab3_web.bean;

import org.icefaces.ace.json.JSONArray;
import org.icefaces.ace.json.JSONException;
import org.icefaces.ace.json.JSONObject;
import souress.lab3_web.database.dao.DataAO;
import souress.lab3_web.util.AreaCheck;
import souress.lab3_web.util.Shots;
import souress.lab3_web.util.Validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Named("dataBean")
@SessionScoped
public class DataBean implements Serializable {
    private final Validation validation;
    private final AreaCheck areaCheck;
    private final DataAO dataAO;
    private double x, y, r;
    private final String sessionId;

    public List<Shots> getHistory() {
        List<Shots> list = dataAO.getShots(sessionId);
        if (list != null)
            list.sort(Comparator.comparingInt(Shots::getId));
        return list;
    }

    public void clearTable() {
        dataAO.clearTable(sessionId);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public DataBean() {
        dataAO = new DataAO();
        validation = new Validation();
        areaCheck = new AreaCheck();
        x = 0;
        y = 0;
        r = 0;
        sessionId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
    }

    public void submit() {
        if (validation.checkAll(x, y, r)) {
            long start = System.nanoTime();
            Shots shot = new Shots();
            shot.setX(round(x));
            shot.setY(round(y));
            shot.setR(r);
            shot.setCurrentTime(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            shot.setDuration((System.nanoTime() - start) / 1000);
            shot.setResult(areaCheck.isInArea(x, y, r));
            shot.setSessionId(sessionId);
            dataAO.addShot(shot);
        }
    }

    public String getHistoryJSONString() {
        JSONArray arr = new JSONArray();
        JSONObject obj;
        List<Shots> history = getHistory();
        if (history != null)
            try {
                for (Shots shots : history) {
                    obj = new JSONObject();
                    obj.put("x", shots.getX());
                    obj.put("y", shots.getY());
                    obj.put("r", shots.getR());
                    obj.put("result", shots.isResult());
                    arr.put(obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return arr.toString();
    }

    private static double round(double value) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
