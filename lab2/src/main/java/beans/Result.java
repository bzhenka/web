package beans;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Result implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Double x;
    private Double y;
    private Double r;
    private boolean hit;
    private String time;
    private long duration;

    public Result() {}

    public Result(Double x, Double y, Double r, boolean hit, String time, long duration){
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.time = time;
        this.duration = duration;
    }
    public Double getX(){
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return hit == result.hit && duration == result.duration && Objects.equals(x, result.x) && Objects.equals(y, result.y) && Objects.equals(r, result.r) && Objects.equals(time, result.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r, hit, time, duration);
    }

    @Override
    public String toString() {
        return "Result{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                ", time='" + time + '\'' +
                ", duration=" + duration +
                '}';
    }
}
