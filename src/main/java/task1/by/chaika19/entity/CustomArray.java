package task1.by.chaika19.entity;

import java.util.Arrays;

public class CustomArray {
    private Long id;
    private int[] array;

    private CustomArray() {
        this.array = new int[0];
    }

    private CustomArray(int[] array) {
        if (array != null) {
            this.array = array.clone();
        } else {
            this.array = new int[0];
        }
    }

    public int[] getArray() {
        return this.array.clone();
    }

    public static CustomArrayBuilder builder() {
        return new CustomArrayBuilder();
    }

    public static class CustomArrayBuilder {
        private int[] array;

        private CustomArrayBuilder() {
        }

        public CustomArrayBuilder withArray(int[] array) {
            this.array = array;
            return this;
        }

        public CustomArray build() {
            return new CustomArray(this.array);
        }
    }

    @Override
    public String toString() {
        return "CustomArray [array=" + Arrays.toString(array) + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object != null && getClass() == object.getClass()) {
            CustomArray that = (CustomArray) object;
            return Arrays.equals(array, that.array);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
