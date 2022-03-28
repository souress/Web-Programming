package souress.lab_web4.util;

import java.io.Serializable;

public class Validation implements Serializable {
    private static boolean checkR(double r) {
        return r > 0 && r <= 2;
    }

    private static boolean checkY(double y) {
        return y >= -5 && y <= 3;
    }

    private static boolean checkX(double x) {
        return x >= -2 && x <= 2;
    }

    public static boolean checkAll(double x, double y, double r) {
        return checkR(r) && checkX(x) && checkY(y);
    }
}
