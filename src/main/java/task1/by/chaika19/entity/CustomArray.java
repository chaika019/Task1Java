package task1.by.chaika19.entity;

import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.observer.CustomArrayObservable;
import task1.by.chaika19.observer.CustomArrayObserver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CustomArray implements CustomArrayObservable {
    private final String id;
    private int[] array;
    private final List<CustomArrayObserver> observers = new ArrayList<>();


    private CustomArray() {
        this.id = generateNextId();
        this.array = new int[0];
    }

    private CustomArray(String id, int[] array) {
        this.id = (id != null) ? id : generateNextId();

        if (array != null) {
            this.array = array.clone();
        } else {
            this.array = new int[0];
        }
    }

    private static String generateNextId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public int[] getArray() {
        return this.array.clone();
    }

    @Override
    public void attach(CustomArrayObserver observer) {
        if (observer != null && !this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    @Override
    public void detach(CustomArrayObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (CustomArrayObserver observer : observers) {
            observer.parameterChanged(this);
        }
    }

//    Stream example
//
//    @Override
//    public void notifyObservers() {
//        observers.stream()
//                .forEach(observer -> observer.parameterChanged(this));
//    }

    public void setElement(int index, int value) throws CustomArrayException {
        if (this.array == null || index < 0 || index >= this.array.length) {
            throw new CustomArrayException("Invalid index " + index +
                    " for array " + id +
                    " with length " + (this.array == null ? 0 : this.array.length));
        }
        this.array[index] = value;

        this.notifyObservers();
    }

    public static CustomArrayBuilder builder() {
        return new CustomArrayBuilder();
    }

    public static class CustomArrayBuilder {
        private String id;
        private int[] array;

        private CustomArrayBuilder() {
        }

        public CustomArrayBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public CustomArrayBuilder withArray(int[] array) {
            this.array = array;
            return this;
        }

        public CustomArray build() {
            return new CustomArray(this.id, this.array);
        }
    }

    @Override
    public String toString() {
        return new StringBuilder("CustomArray [id=")
                .append(id)
                .append(", array=")
                .append(Arrays.toString(array))
                .append("]")
                .toString();
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
        final int prime = 31;
        int result = 1;

        result = prime * result + Arrays.hashCode(array);

        return result;
    }
}
