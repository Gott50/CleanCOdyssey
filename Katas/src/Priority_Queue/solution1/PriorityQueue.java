package Priority_Queue.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.TreeSet;

class PriorityQueue<T> {
    private final Set<Queue> queues = new TreeSet<>();

    public int count() {
        return queues.size();
    }

    void enqueue(T element, int priority) {
        queues.add(new Queue(element, priority));
    }

    T dequeue() {
        Queue out = queues.iterator().next();
        queues.remove(out);
        return out.element;
    }

    private class Queue implements Comparable {
        private final T element;
        private final int priority;

        Queue(T element, int priority) {
            this.element = element;
            this.priority = priority;
        }

        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(@NotNull Object o) {
            if (((Queue) o).element.equals(element)) return 0;

            return -1;
        }
    }
}
