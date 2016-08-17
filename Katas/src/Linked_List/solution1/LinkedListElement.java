package Linked_List.solution1;

import java.util.ArrayList;

class LinkedListElement<T> {
    private T item;
    private LinkedListElement<T> next, prev;

    LinkedListElement(T item) {
        setItem(item);
    }

    public T getItem() {
        return item;
    }

    public LinkedListElement<T> setItem(T item) {
        this.item = item;
        return this;
    }

    LinkedListElement<T> getNext() {
        return next;
    }

    LinkedListElement<T> setNext(T item) {
        this.next = new LinkedListElement<>(item);
        return this.next;
    }

    void setNext(LinkedListElement<T> next) {
        this.next = next;
        this.next.prev = this;
    }

    ArrayList<T> toArray() {
        ArrayList<T> out = new ArrayList<>();
        LinkedListElement<T> list = this;
        while (list != null) {
            out.add(list.getItem());
            list = list.getNext();
        }
        return out;
    }

    LinkedListElement<T> getPrev() {
        return prev;
    }

    void setPrev(LinkedListElement<T> prev) {
        this.prev = prev;
        this.prev.next = this;
    }

    LinkedListElement getFirst() {
        LinkedListElement<T> first = this;
        while (first.prev != null)
            first = first.getPrev();

        return first;
    }
}
