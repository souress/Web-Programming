package servlets;

import util.Data;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.nanoTime();
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

        double x;
        double y;
        double r;
        try {
            x = Double.parseDouble(req.getParameter("X"));
            y = Double.parseDouble(req.getParameter("Y"));
            r = Double.parseDouble(req.getParameter("R"));
        } catch (NumberFormatException exception) {
            resp.setStatus(420);
            return;
        }
        String result = isHit(x, y, r) ? "Есть" : "Нет";

        double duration = (System.nanoTime() - start) / 1000.0;

        HttpSession session = req.getSession();

        Object rawHistory = session.getAttribute("history");
        LinkedList<Data> history;
        if (rawHistory != null) {
            if (rawHistory instanceof LinkedList
                    && !((LinkedList<?>) rawHistory).isEmpty()
                    && ((LinkedList<?>) rawHistory).getFirst() instanceof Data) {
                history = (LinkedList<Data>) rawHistory;
            } else {
                System.out.println("Не удалось загрузить предыдущие попытки");
                history = new LinkedList<>();
            }
        } else {
            history = new LinkedList<>();
        }
        Data data = new Data();
        data.setX(Math.round(x * 100.0) / 100.0);
        data.setY(Math.round(y * 100.0) / 100.0);
        data.setR(Math.round(r * 100.0) / 100.0);
        data.setCurrentTime(currentTime);
        data.setDuration(Double.toString(duration));
        data.setResult(result);
        history.add(data);

        session.setAttribute("history", history);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().println(data.dataToJSON());
    }

    private boolean isHit(double x, double y, double r) {
        if ((x >= -r && x <= 0 && y <= 0 && y >= -r/2) ||
                (x >= 0 && x <= r && y >= x - r && y >= -r && y <= 0)) {
            return true;
        } else return x >= 0 && x <= r && x * x + y * y <= r * r && y <= r && y >= 0;
    }
}
