package Circular_Buffer.solution1;

import java.util.ArrayList;

class CircularBuffer<T> {
    private final ArrayList<T> elements = new ArrayList<>();
    private final int size;

    CircularBuffer(int size) {
        this.size = size;
    }

    int getSize() {
        return size;
    }

    void add(T element) {
        if (count() >= getSize()) take();

        elements.add(element);
    }

    T take() {
        return elements.remove(0);
    }

    int count() {
        return elements.size();
    }
}
