package example.utils;

public class AreaChecker {
    public static boolean isInArea(Double x, Double y, Double r) {
        if (x <= 0 && y <= 0) {
            return (x * x + y * y) <= r * r;
        }
        if (x >= 0 && y >= 0) {
            return (x <= r) && (2 * y <= r);
        }
        if (x >= 0 && y <= 0) {
            return (2 * (x + Math.abs(y)) <= r);
        }
        return false;
    }
}
