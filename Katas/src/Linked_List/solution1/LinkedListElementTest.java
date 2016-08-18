package Linked_List.solution1;

import org.junit.Test;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertEquals;

@SuppressWarnings("unchecked")
public class LinkedListElementTest {

    private final LinkedListElement<Integer> list = new LinkedListElement((Integer) 1);


    @Test
    public void constructor() throws Exception {
        assertEquals((Integer) 1, list.getItem());
    }

    @Test
    public void setItem() throws Exception {
        list.setItem(10);
        assertEquals((Integer) 10, list.getItem());
    }

    @Test
    public void setNext() throws Exception {
        list.setNext(new LinkedListElement(11));
        assertEquals((Integer) 11, list.getNext().getItem());
    }

    @Test
    public void toArray() throws Exception {
        list.setNext(2).setNext(3).setNext(4).setNext(5);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list.toArray());
    }

    @Test
    public void setPrev() throws Exception {
        list.setPrev(new LinkedListElement(11));
        assertEquals(Arrays.asList(11, 1), list.getPrev().toArray());
    }

    @Test
    public void getFist() throws Exception {
        list.setPrev(new LinkedListElement(11));
        list.setNext(2);
        assertEquals(Arrays.asList(11, 1, 2), list.getFirst().toArray());
    }
}