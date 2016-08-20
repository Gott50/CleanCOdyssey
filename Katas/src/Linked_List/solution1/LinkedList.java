package Linked_List.solution1;

import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class LinkedList<T> implements List {
    private LinkedListElement<T> elements;

    @Override
    public int size() {
        return elements != null ? elements.toArray().size() : 0;
    }

    @Override
    public boolean isEmpty() {
        return elements == null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public @NotNull Iterator iterator() {
        return null;
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return Arrays.asList(elements).toArray();
    }

    @Override
    public boolean add(@Flow(targetIsContainer = true) Object o) {
        try {
            if (elements == null) elements = new LinkedListElement<T>((T) o);
            else elements.getLast().setNext(o);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(@Flow(sourceIsContainer = true, targetIsContainer = true) Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, @Flow(sourceIsContainer = true, targetIsContainer = true) Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return elements.toArray().get(index);
    }

    @Override
    public Object set(int index, @Flow(targetIsContainer = true) Object element) {
        return null;
    }

    @Override
    public void add(int index, @Flow(targetIsContainer = true) Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public @NotNull ListIterator listIterator() {
        return null;
    }

    @NotNull
    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @NotNull
    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
