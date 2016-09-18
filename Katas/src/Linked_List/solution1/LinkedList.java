package Linked_List.solution1;

import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class LinkedList<T> implements List {
    private LinkedListElement<T> elements;

    @Override
    public int size() {
        return elements != null ? elements.toArrayList().size() : 0;
    }

    @Override
    public boolean isEmpty() {
        return elements == null;
    }

    @Override
    public boolean contains(Object o) {
        return elements.toArrayList().contains(o);
    }

    @Override
    public @NotNull Iterator iterator() {
        return elements.toArrayList().iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        Object[] out = new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            out[i] = this.get(i);
        }
        return out;
    }

    @Override
    public boolean add(@Flow(targetIsContainer = true) Object o) {
        T addition;
        try {
            addition = (T) o;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (elements == null) elements = new LinkedListElement<>(addition);
        else elements.addLast(addition);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean contains = contains(o);
        remove(elements.toArrayList().indexOf(o));

        return contains;
    }

    @Override
    public boolean addAll(@Flow(sourceIsContainer = true, targetIsContainer = true) Collection c) {
        try {
            for (Object element : c.toArray()) {
                add(element);
            }
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    public boolean addAll(int index, @Flow(sourceIsContainer = true, targetIsContainer = true) Collection c) {
        return false;
    }

    @Override
    public void clear() {
        elements = null;
    }

    @Override
    public Object get(int index) {
        return elements.toArrayList().get(index);
    }

    @Override
    public Object set(int index, @Flow(targetIsContainer = true) Object element) {
        Object out = get(index);

        elements.get(index).setItem((T) element);

        return out;
    }

    @Override
    public void add(int index, @Flow(targetIsContainer = true) Object element) {

    }

    @Override
    public Object remove(int index) {
        Object out = get(index);

        elements.get(index - 1).setNext(elements.get(index + 1));

        return out;
    }

    @Override
    public int indexOf(Object o) {
        return elements.toArrayList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return elements.toArrayList().lastIndexOf(o);
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
        @NotNull List out = new LinkedList<T>();

        out.addAll(elements.toArrayList().subList(fromIndex, toIndex));

        return out;
    }

    @Override
    public boolean retainAll(Collection c) {
        try {
            ArrayList<T> toRemove = elements.toArrayList();
            toRemove.removeAll(c);
            this.removeAll(toRemove);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        try {
            for (Object o : c) {
                remove(o);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @NotNull
    @Override
    public Object[] toArray(Object[] a) {
        try {
            @NotNull Object[] listArray = this.toArray();
            for (int i = 0; i < listArray.length; i++) {
                a[i] = listArray[i];
            }
            return a;
        } catch (Exception e) {
            return a;
        }
    }
}
