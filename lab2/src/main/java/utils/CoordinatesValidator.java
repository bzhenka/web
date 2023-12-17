package utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CoordinatesValidator {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal r;

    public CoordinatesValidator(BigDecimal x, BigDecimal y, BigDecimal r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean checkData() {
        if (!checkX()) System.out.println("Значение X не правильное");
        if (!checkY()) System.out.println("Значение y не правильное");
        if (!checkR()) System.out.println("Значение R не правильное");
        return checkX() && checkY() && checkR();
    }

    private boolean checkX() {
        List<BigDecimal> validXValues = Arrays.asList(
                new BigDecimal("-3"), new BigDecimal("-2"), new BigDecimal("-1"),
                new BigDecimal("0"), new BigDecimal("1"), new BigDecimal("2"),
                new BigDecimal("3"), new BigDecimal("4"), new BigDecimal("5")
        );
        for (BigDecimal validX : validXValues) {
            if (validX.compareTo(this.x) == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean checkY() {
        return y.compareTo(new BigDecimal("-5")) >= 0 && y.compareTo(new BigDecimal("3")) <= 0;
    }

    private boolean checkR() {
        List<BigDecimal> validRValues = Arrays.asList(
                new BigDecimal("1"), new BigDecimal("1.5"), new BigDecimal("2"),
                new BigDecimal("2.5"), new BigDecimal("3")
        );
        for (BigDecimal validR: validRValues) {
            if (validR.compareTo(this.r) == 0) {
                return true;
            }
        }
        return false;
    }
}

