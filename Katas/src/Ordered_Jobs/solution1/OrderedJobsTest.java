package Ordered_Jobs.solution1;

import org.jetbrains.annotations.NotNull;
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
        final ArrayList<Job> jobs = new ArrayList<>();

        @Override
        public void register(char dependentJob, char independentJob) {
            register(dependentJob);
            addDependency(dependentJob, independentJob);
        }

        private void addDependency(char dependentJob, char independentJob) {
            register(independentJob);
            Job dependency = this.getJob(independentJob);
            jobs.get(getIndex(dependentJob)).addDependency(dependency);
        }

        private Job getJob(char jobID) {
            return jobs.get(getIndex(jobID));
        }


        @Override
        public void register(char jobID) {
            Job job = new Job(jobID);
            if (!isIDRegenerated(jobID)) {
                jobs.add(job);
            }
        }


        private boolean isIDRegenerated(char jobID) {
            return getIndex(jobID) >= 0;
        }

        private int getIndex(char jobID) {
            for (int i = 0; i < jobs.size(); i++) {
                if (jobs.get(i).jobID == jobID) return i;
            }
            return -1;
        }

        @Override
        public String sort() {
            jobs.sort((o1, o2) -> o1.dependencies.size() - o2.dependencies.size());

            return jobsToString();
        }

        @NotNull
        private String jobsToString() {
            String out = "";
            for (Job job : jobs) {
                out += job.jobID;
            }
            return out;
        }

        private class Job {
            private ArrayList<Job> dependencies = new ArrayList<>();
            private char jobID;

            private Job(char jobID) {
                this.jobID = jobID;
            }

            void addDependency(Job dependency) {
                this.dependencies.add(dependency);
            }
        }
    }
}