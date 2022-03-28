package souress.lab3_web.util;

public class AreaCheck {
    public boolean isInArea(double x, double y, double r) {
        return isInRectangle(x, y, r) || isInSector(x, y, r) || isInTriangle(x, y, r);
    }

    private boolean isInSector(double x, double y, double r) {
        return x >= -r && x <= 0 && x * x + y * y <= r * r && y >= -r && y <= 0;
    }

    private boolean isInTriangle(double x, double y, double r) {
        return x >= 0 && x < r/2 - y/2 && y >= 0;
    }

    private boolean isInRectangle(double x, double y, double r) {
        return x >= 0 && x <= r/2 && y >= -r && y <= 0;
    }
}
