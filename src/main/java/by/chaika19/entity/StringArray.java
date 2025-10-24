package by.chaika19.entity;

public class StringArray {
    private String[] array;

    private StringArray() {
        this.array = new String[0];
    }

    private StringArray(String[] array) {
        if (array == null) {
            this.array = new String[0];
        } else {
            this.array = new String[array.length];
            System.arraycopy(array, 0, this.array, 0, array.length);
        }
    }

    public String[] getArray() {
        String[] copyOfArray = new String[array.length];
        System.arraycopy(array, 0, copyOfArray, 0, array.length);
        return copyOfArray;
    }

    public static class StringArrayBuilder {
        private String[] array;

        public StringArray build() {
            return new StringArray(array);
        }

        public StringArrayBuilder withString(String line) {
            this.array = parseLine(line);
            return this;
        }

        private String[] parseLine(String line) {
            if (line == null || line.trim().isEmpty()) {
                return new String[0];
            }
            return line.split(",");
        }
    }

    public static StringArrayBuilder builder() {
        return new StringArrayBuilder();
    }
}
