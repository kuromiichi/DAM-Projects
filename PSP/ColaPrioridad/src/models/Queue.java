package src.models;

import java.util.ArrayList;

public class Queue<T> {

    ArrayList<T> items = new ArrayList<>(0);

    public boolean add(T item) {
        return items.add(item);
    }

    public T remove() {
        return items.remove(0);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String toString() {
        return items.toString();
    }
}
