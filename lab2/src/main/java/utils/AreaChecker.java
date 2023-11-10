package utils;

import static java.lang.Math.abs;

public class AreaChecker {
    public  static boolean isInArea(double x, double y, double r){
        if ( x <= 0 && y >= 0){
            return ((abs(x) + y) <= r/2);
        }
        if ( x >= 0 && y >= 0){
            return (x <= r) && (y <= r/2);
        }
        if ( x >= 0 && y <= 0){
            return (x * x + y * y) <= r*r;
        }
        return false;
    }
}
