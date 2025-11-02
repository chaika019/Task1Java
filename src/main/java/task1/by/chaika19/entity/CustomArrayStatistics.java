package task1.by.chaika19.entity;

public class CustomArrayStatistics {
    private final int min;
    private final int max;
    private final int sum;
    private final double average;

    public CustomArrayStatistics(int min, int max, int sum, double average) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return new StringBuilder("ArrayStatistics [min=")
                .append(min)
                .append(", max=")
                .append(max)
                .append(", sum=")
                .append(sum)
                .append(", avg=")
                .append(average)
                .append("]")
                .toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CustomArrayStatistics that = (CustomArrayStatistics) object;
        return min == that.min &&
                max == that.max &&
                sum == that.sum &&
                Double.compare(that.average, average) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + min;
        result = prime * result + max;
        result = prime * result + sum;
        long temp = Double.doubleToLongBits(average);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}