package problems.p042;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class TestP042_Coded_triangle_numbers {
    private P042_Coded_triangle_numbers.TriangleDetector triangleDetector;

    @Before
    public void setUp() throws Exception {
        triangleDetector = new P042_Coded_triangle_numbers.TriangleDetector();
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(55, triangleDetector.getValue("SKY"));
    }

    @Test
    public void testGetTriangle() throws Exception {
        assertEquals(55, triangleDetector.getTriangel(10));
    }

    @Test
    public void testIsTriangleNumber() throws Exception {
        assertEquals(true, triangleDetector.isTriangel(55));
    }
    @Test
    public void testIsTriangleNumber_False() throws Exception {
        assertEquals(false, triangleDetector.isTriangel(54));
    }

    @Test
    public void testIsTriangleWord() throws Exception {
        assertEquals(true, triangleDetector.isTriangel("SKY"));
    }

    @Test
    public void testGetNumberOfTriangels() throws Exception {
        assertEquals(2, triangleDetector.getNumberOfTriangels("SKY","a"));
    }
}
