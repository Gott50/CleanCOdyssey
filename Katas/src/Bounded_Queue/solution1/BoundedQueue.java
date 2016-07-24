package Bounded_Queue.solution1;

import java.util.ArrayList;
import java.util.List;

class BoundedQueue<T> {
    private final int size;
    private final ArrayList<T> queue;
    private final ArrayList<T> comingIn;


    public BoundedQueue(int size) {
        this.size = size;
        queue = new ArrayList<>();
        comingIn = new ArrayList<>();
    }

    int getSize() {
        return size;
    }

    void enqueue(T value) {
        while (count() >= size) ;

        comingIn.add(value);
        updateQueue();
    }

    synchronized private void updateQueue() {
        if (count() != size)
            if (comingIn.size() > 0) {
                queue.add(getNextValue());
            }
    }


    List<T> values() {
        return queue;
    }

    T dequeue() {
        T out;

        while (count() == 0) ;

        out = pullValue();

        return out;
    }

    private T pullValue() {
        T out;
        out = queue.get(0);
        queue.remove(0);
        return out;
    }

    int count() {
        return values().size();
    }

    private T getNextValue() {
        T nextValue = comingIn.get(0);
        comingIn.remove(0);
        return nextValue;
    }
}
