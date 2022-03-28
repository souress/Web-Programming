package souress.lab3_web.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.time.ZonedDateTime;

@Named("timeBean")
@ApplicationScoped
public class TimeBean {
    private String format(int value) {
        String num = String.valueOf(value);
        if (num.length() == 1)
            return "0" + num;
        else return num;
    }

    public String getDay() {
        return format(ZonedDateTime.now().getDayOfMonth());
    }

    public String getMonth() {
        return format(ZonedDateTime.now().getMonthValue());
    }

    public String getYear() {
        return format(ZonedDateTime.now().getYear());
    }

    public String getHour() {
        return format(ZonedDateTime.now().getHour());
    }

    public String getMin() {
        return format(ZonedDateTime.now().getMinute());
    }

    public String getSec() {
        return format(ZonedDateTime.now().getSecond());
    }
}
