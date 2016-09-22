package Ordered_Jobs.solution1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class OrderedJobsTest {
    private OrderedJobs jobs;

    @Before
    public void setUp() throws Exception {
        jobs = new OrderedJobsImpl();
    }

    @Test
    public void givenNull_sortReturnsEmptyString() throws Exception {
        assertEquals("", jobs.sort());
    }

    @Test
    public void givenRegisterA_sortReturnsA() throws Exception {
        jobs.register('A');
        assertEquals("A", jobs.sort());
    }

    @Test
    public void givenMultipleRegistrationsOfA_sortReturnsA() throws Exception {
        jobs.register('A');
        jobs.register('A');
        jobs.register('A');
        assertEquals("A", jobs.sort());
    }

    @Test
    public void givenRegisterBDependentOnA_sortReturnsAB() throws Exception {
        jobs.register('B', 'A');
        assertEquals("AB", jobs.sort());
    }

    @Test
    public void integrationABC() throws Exception {
        jobs.register('C');
        jobs.register('B', 'A');
        jobs.register('C', 'B');
        assertEquals("ABC", jobs.sort());
    }


    private class OrderedJobsImpl implements OrderedJobs {
        final ArrayList<Character> jobs = new ArrayList<>();

        @Override
        public void register(char dependentJob, char independentJob) {
            register(independentJob);
            register(dependentJob);
        }

        @Override
        public void register(char dependentJob) {
            if (!jobs.contains(dependentJob))
                jobs.add(dependentJob);
        }

        @Override
        public String sort() {
            String out = "";
            for (Character job : jobs) {
                out += job;
            }
            return out;
        }
    }
}