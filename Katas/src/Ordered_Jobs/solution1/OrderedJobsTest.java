package Ordered_Jobs.solution1;

import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class OrderedJobsTest {
    private OrderedJobs jobs;

    @Before
    public void setUp() throws Exception {
        jobs = new OrderedJobsImpl();
    }

    @Test
    public void givenNull_sortReturnsEmptyString() throws Exception {
        assertSort("");
    }

    @Test
    public void givenRegisterA_sortReturnsA() throws Exception {
        jobs.register('A');
        assertSort("A");
    }

    @Test
    public void givenMultipleRegistrationsOfA_sortReturnsA() throws Exception {
        jobs.register('A');
        jobs.register('A');
        jobs.register('A');
        assertSort("A");
    }

    @Test
    public void givenRegisterBDependentOnA_sortReturnsAB() throws Exception {
        jobs.register('B', 'A');
        assertSort("AB");
    }

    @Test
    public void givenRegisterBDependentOnAAndCAndD_sortReturnsACDB() throws Exception {
        jobs.register('C', 'A');
        jobs.register('C', 'B');
        assertSort("ABC");
    }

    @Test
    public void integrationABC() throws Exception {
        jobs.register('C');
        jobs.register('B', 'A');
        jobs.register('C', 'B');
        assertSort("ABC");
    }

    private void assertSort(String expected) {
        assertEquals(expected, jobs.sort());
    }

    @Test
    public void givenSortMultiLineText_sortReturnsABC() throws Exception {
        String input = "C =>\n" +
                "    B => A\n" +
                "    C => B";
        assertEquals("ABC", jobs.sort(input));
    }


}