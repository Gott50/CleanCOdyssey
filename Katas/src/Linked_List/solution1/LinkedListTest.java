package Linked_List.solution1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @Before
    public void setUp() throws Exception {
        list = new LinkedList<>();
    }

    @Test
    public void size_emty() throws Exception {
        assertEquals(0, list.size());
    }

    @Test
    public void size_notEmty() throws Exception {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    @Ignore
    public void isEmpty() throws Exception {

    }

    @Test
    @Ignore
    public void contains() throws Exception {

    }

    @Test
    @Ignore
    public void iterator() throws Exception {

    }

    @Test
    @Ignore
    public void toArray() throws Exception {

    }

    @Test
    public void add() throws Exception {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    @Ignore
    public void remove() throws Exception {

    }

    @Test
    @Ignore
    public void addAll() throws Exception {

    }

    @Test
    @Ignore
    public void addAll1() throws Exception {

    }

    @Test
    @Ignore
    public void clear() throws Exception {

    }

    @Test
    @Ignore
    public void get() throws Exception {

    }

    @Test
    @Ignore
    public void set() throws Exception {

    }

    @Test
    @Ignore
    public void add1() throws Exception {

    }

    @Test
    @Ignore
    public void remove1() throws Exception {

    }

    @Test
    @Ignore
    public void indexOf() throws Exception {

    }

    @Test
    @Ignore
    public void lastIndexOf() throws Exception {

    }

    @Test
    @Ignore
    public void listIterator() throws Exception {

    }

    @Test
    @Ignore
    public void listIterator1() throws Exception {

    }

    @Test
    @Ignore
    public void subList() throws Exception {

    }

    @Test
    @Ignore
    public void retainAll() throws Exception {

    }

    @Test
    @Ignore
    public void removeAll() throws Exception {

    }

    @Test
    @Ignore
    public void containsAll() throws Exception {

    }

    @Test
    @Ignore
    public void toArray1() throws Exception {

    }


}