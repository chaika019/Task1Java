package by.chaika19.task1.entity;

import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.observer.CustomArrayObservable;
import by.chaika19.task1.observer.CustomArrayObserver;
import by.chaika19.task1.util.IdGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomArray implements CustomArrayObservable {
    private final String id;
    private final int[] array;
    private final List<CustomArrayObserver> observers = new ArrayList<>();

    private CustomArray() {
        this.id =  IdGenerator.generateId();
        this.array = new int[0];
    }

    private CustomArray(String id, int[] array) {
        this.id = (id != null) ? id :  IdGenerator.generateId();

        if (array != null) {
            this.array = array.clone();
        } else {
            this.array = new int[0];
        }
    }

    public String getId() {
        return id;
    }

    public int[] getArray() {
        return this.array.clone();
    }

    public void setElement(int index, int value) throws CustomArrayException {
        if (this.array == null || index < 0 || index >= this.array.length) {
            throw new CustomArrayException("Invalid index " + index +
                    " for array " + id +
                    " with length " + (this.array == null ? 0 : this.array.length));
        }
        this.array[index] = value;

        this.notifyObservers();
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
        observers.forEach(observer -> observer.parameterChanged(this));
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
