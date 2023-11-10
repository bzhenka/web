package utils;

import java.util.Arrays;
import java.util.List;

public class CoordinatesValidator {
    private final double x;
    private final double y;
    private final double r;

    public CoordinatesValidator(double x, double y, double r) {
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
        List<Integer> validXValues = Arrays.asList(-3, -2, -1, 0, 1, 2, 3, 4, 5);
        return validXValues.contains((int) x) && x == (int) x;
    }
    private boolean checkY() {
        return y >= -5 && y <=3;
    }
    private boolean checkR() {
        List<Double> validRValues = Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0);
        return validRValues.contains(r);
    }
}
