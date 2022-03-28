package souress.lab_web4.util;

public class AreaCheck {
    public static boolean isInArea(double x, double y, double r) {
        return isInRectangle(x, y, r) || isInSector(x, y, r) || isInTriangle(x, y, r);
    }

    private static boolean isInSector(double x, double y, double r) {
        return x <= r/2 && x >= 0 && x * x + y * y <= r * r / 4 && y >= -r/2 && y <= 0;
    }

    private static boolean isInTriangle(double x, double y, double r) {
        return x <= 0 && x > -y - r && y <= 0;
    }

    private static boolean isInRectangle(double x, double y, double r) {
        return x <= 0 && x >= -r && y >= 0 && y <= r/2;
    }
}
