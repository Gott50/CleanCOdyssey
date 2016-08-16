package Linked_List.solution1;

import java.util.ArrayList;

class LinkedList<T> {
    private T item;
    private LinkedList<T> next;

    LinkedList(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public LinkedList<T> setItem(T item) {
        this.item = item;
        return this;
    }

    LinkedList<T> getNext() {
        return next;
    }

    LinkedList<T> setNext(T item) {
        this.next = new LinkedList<>(item);
        return this.next;
    }

    void setNext(LinkedList<T> next) {
        this.next = next;
    }

    ArrayList<T> toArray() {
        ArrayList<T> out = new ArrayList<>();
        LinkedList<T> list = this;
        while (list != null) {
            out.add(list.getItem());
            list = list.getNext();
        }
        return out;
    }
}
