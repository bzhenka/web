package utils;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class AreaChecker {
    public static boolean isInArea(BigDecimal x, BigDecimal y, BigDecimal r) {
        if (x.compareTo(ZERO) <= 0 && y.compareTo(ZERO) >= 0) {
            return (x.abs().add(y).multiply(BigDecimal.valueOf(2))).compareTo(r) <= 0;
        }
        if (x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0) {
            return (x.compareTo(r) <= 0) && (y.multiply(BigDecimal.valueOf(2)).compareTo(r) <= 0);
        }
        if (x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) <= 0) {
            return (x.multiply(x).add(y.multiply(y))).compareTo(r.multiply(r)) <= 0;
        }
        return false;
    }
}

