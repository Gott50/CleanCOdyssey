package Linked_List.solution1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

import static org.testng.AssertJUnit.*;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new LinkedList<>();
    }

    @Test
    public void size_empty() throws Exception {
        assertEquals(0, list.size());
    }

    @Test
    public void size_notEmpty() throws Exception {
        addNumbers(2);
        assertEquals(2, list.size());
    }

    @Test
    public void isEmpty_givenEmpty() throws Exception {
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void isEmpty_givenNotEmpty() throws Exception {
        list.add(1);
        assertEquals(false, list.isEmpty());
    }

    @Test
    public void contains() throws Exception {
        addNumbers(3);
        assertTrue(list.contains(2));
        assertFalse(list.contains(0));
    }

    private void addNumbers(int to) {
        for (int i = 1; i <= to; i++)
            list.add(i);
    }

    @Test
    public void iterator() throws Exception {
        addNumbers(5);
        assertIterator(list.iterator(), 1, 2, 3, 4, 5);
    }

    @Test
    public void toArray() throws Exception {
        addNumbers(5);
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, list.toArray());
    }

    @Test
    public void toArray_ObjectArray_givenBigEnoughArray() throws Exception {
        addNumbers(5);

        Integer[] bigEnough = new Integer[5];
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(bigEnough));
    }

    @Test
    public void toArray_ObjectArray_givenToSmallArray() throws Exception {
        addNumbers(5);

        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, list.toArray(new Integer[0]));
    }

    @Test
    public void add() throws Exception {
        addNumbers(3);
        assertEquals(3, list.size());
    }

    @Test
    public void add_index() throws Exception {
        addNumbers(5);
        list.add(3, 6);
        assertArrayEquals(new Object[]{1, 2, 3, 6, 4, 5}, list.toArray());
    }

    @Test
    public void remove_Index() throws Exception {
        addNumbers(5);

        assertEquals(2, list.remove(1));

        assertRemove();
    }

    @Test

    public void remove_Object() throws Exception {
        addNumbers(5);

        assertEquals(true, list.remove((Object) 2));

        assertRemove();
    }

    private void assertRemove() {
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(4, list.size());
    }

    @Test
    public void addAll_withoutIndex() throws Exception {
        Object[] expects = {1, 2, 3, 4, 5};

        assertTrue(list.addAll(Arrays.asList(expects)));
        assertArrayEquals(expects, list.toArray());
    }

    @Test
    public void addAll_withIndex() throws Exception {

        assertTrue(list.addAll(2, Arrays.asList(1, 2, 3, 4, 5)));
        assertArrayEquals(new Object[]{3, 4, 5}, list.toArray());
    }

    @Test
    public void clear() throws Exception {
        addNumbers(5);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void get() throws Exception {
        addNumbers(3);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void set() throws Exception {
        addNumbers(5);

        assertEquals(2, list.set(1, 5));

        assertEquals(5, list.get(1));
        assertEquals(5, list.size());
    }


    @Test
    public void indexOf() throws Exception {
        addNumbers(5);
        list.add(2);

        assertEquals(1, list.indexOf(2));
    }

    @Test
    public void lastIndexOf() throws Exception {
        addNumbers(5);
        list.add(2);

        assertEquals(5, list.lastIndexOf(2));
    }

    @Test
    public void listIterator() throws Exception {
        addNumbers(5);
        ListIterator listIterator = list.listIterator();
        assertIterator(listIterator, 1, 2, 3, 4, 5);
    }

    private void assertIterator(Iterator listIterator, Object... expectedElements) {
        for (Object element : expectedElements) {
            assertEquals(element, listIterator.next());
        }
    }

    @Test
    public void listIterator_withIndex() throws Exception {
        addNumbers(5);
        ListIterator listIterator = list.listIterator(2);
        assertIterator(listIterator, 3, 4, 5);
    }

    @Test
    public void subList() throws Exception {
        addNumbers(6);
        assertArrayEquals(new Object[]{3, 4}, list.subList(2, 4).toArray());
    }

    @Test
    public void retainAll() throws Exception {
        addNumbers(5);

        assertTrue(list.retainAll(Arrays.asList(1, 2, 5)));
        assertArrayEquals(new Object[]{1, 2, 5}, list.toArray());
    }

    @Test
    public void removeAll() throws Exception {
        addNumbers(5);

        assertTrue(list.removeAll(Arrays.asList(3, 4)));
        assertArrayEquals(new Object[]{1, 2, 5}, list.toArray());
    }

    @Test
    public void containsAll() throws Exception {
        addNumbers(5);

        assertTrue(list.containsAll(Arrays.asList(3, 4)));
    }

}