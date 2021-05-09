package pl.sdacademy.filmscorer.domain;

public class Score {
    private final double value;
    private final int count;

    public Score(double value, int count) {
        this.value = value;
        this.count = count;
    }

    public double getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value + //TODO ta wartość powinna być z dokładnością do dwóch miejsc po przecinku
                ", count=" + count +
                '}';
    }
}
