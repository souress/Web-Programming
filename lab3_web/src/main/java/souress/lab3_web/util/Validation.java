package souress.lab3_web.util;

import java.io.Serializable;

public class Validation implements Serializable {
    public boolean checkR(double r) {
        return r >= 1 && r <= 5;
    }

    public boolean checkY(double y) {
        return y >= -3 && y <= 3;
    }

    public boolean checkX(double x) {
        return x >= -5 && x <= 5;
    }

    public boolean checkAll(double x, double y, double r) {
        return checkR(r) && checkX(x) && checkY(y);
    }
}
