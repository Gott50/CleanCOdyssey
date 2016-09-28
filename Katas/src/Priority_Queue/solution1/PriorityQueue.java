package Priority_Queue.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
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
        Iterator<Queue> iterator = queues.iterator();
        Queue out = iterator.next();
        iterator.remove();
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

            int order = ((Queue) o).priority - priority;
            return order == 0 ? 1 : order;
        }
    }
}
