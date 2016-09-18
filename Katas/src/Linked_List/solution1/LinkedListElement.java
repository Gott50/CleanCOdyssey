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

    public void setItem(T item) {
        this.item = item;
    }

    LinkedListElement<T> getNext() {
        return next;
    }

    void setNext(LinkedListElement<T> next) {
        this.next = next;
        this.next.prev = this;
    }

    LinkedListElement<T> setNext(T item) {
        this.next = new LinkedListElement<>(item);
        return this.next;
    }

    ArrayList<T> toArrayList() {
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

    LinkedListElement<T> getFirst() {
        LinkedListElement<T> first = this;
        while (first.prev != null)
            first = first.getPrev();

        return first;
    }

    private LinkedListElement getLast() {
        LinkedListElement<T> last = this;
        while (last.next != null)
            last = last.getNext();

        return last;
    }

    LinkedListElement<T> get(int index) {
        LinkedListElement<T> current = getFirst();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }


    void addLast(T o) {
        this.getLast().setNext(o);
    }
}
