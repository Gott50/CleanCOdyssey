package Linked_List.solution1;

import org.junit.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

public class LinkedListTest {

    @Test
    public void setItem() throws Exception {
        LinkedList list = new LinkedList(1);
        list.setItem(10);
        assertEquals(10, list.getItem());
    }

    @Test
    public void setNext() throws Exception {
        LinkedList list = new LinkedList(10);
        list.setNext(new LinkedList(11));
        assertEquals(11, list.getNext().getItem());
    }

    @Test
    public void toArray() throws Exception {
        LinkedList list = new LinkedList(1);
        list.setNext(2).setNext(3).setNext(4).setNext(5);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list.toArray());
    }


}