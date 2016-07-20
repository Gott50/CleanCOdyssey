package Bounded_Queue.solution1;

import java.util.ArrayList;
import java.util.List;

class BoundedQueue<T> {
    private final int size;
    private ArrayList<T> queue;


    public BoundedQueue(int size) {
        this.size = size;
        if (queue == null)
            queue = new ArrayList<>();
    }

    int getSize() {
        return size;
    }

    void enqueue(T value) {
        queue.add(value);

    }

    List<T> values() {
        return queue;
    }

    T dequeue() {
        int lastIndex = queue.size() - 1;
        T out = queue.get(lastIndex);
        queue.remove(lastIndex);
        return out;
    }

    int count() {
        return values().size();
    }
}
