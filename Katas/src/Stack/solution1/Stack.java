package Stack.solution1;

import java.util.ArrayList;
import java.util.Arrays;

class Stack<T> {

    private ArrayList<T> list = new ArrayList<>();

    T pop() throws IllegalOperationException {
        if (list.isEmpty())
            throw new IllegalOperationException();
        return list.remove(list.size() - 1);
    }

    void push(T element) {
        list.add(element);
    }

    Object[] toArray() {
        return list.toArray();
    }

    @SafeVarargs
    final void setElements(T... elements) {
        this.list = new ArrayList<>(Arrays.asList(elements));
    }
}
