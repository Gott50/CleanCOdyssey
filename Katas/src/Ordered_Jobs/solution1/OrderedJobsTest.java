package Ordered_Jobs.solution1;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

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
    public void givenRegisterBDependentOnAAndCAndD_sortReturnsACDB() throws Exception {
        jobs.register('B', 'A');
        jobs.register('B', 'C');
        jobs.register('B', 'D');
        assertEquals("ACDB", jobs.sort());
    }

    @Test
    public void integrationABC() throws Exception {
        jobs.register('C');
        jobs.register('B', 'A');
        jobs.register('C', 'B');
        assertEquals("ABC", jobs.sort());
    }


    private class OrderedJobsImpl implements OrderedJobs {
        final TreeSet<Job> jobs = new TreeSet<>();

        @Override
        public void register(char dependentJob, char independentJob) {
            addDependency(registerJob(dependentJob), independentJob);
        }

        private void addDependency(Job dependentJob, char independentJobID) {
            Job dependency = registerJob(independentJobID);
            dependentJob.addDependency(dependency);
        }

        private Job getJob(char jobID) {
            for (Job element : jobs) {
                if (element.jobID == jobID) return element;
            }
            return null;
        }


        @Override
        public void register(char jobID) {
            registerJob(jobID);
        }

        private Job registerJob(char jobID) {
            Job job = new Job(jobID);
            if (!isRegistered(jobID) && jobs.add(job))
                return job;
            else return this.getJob(jobID);
        }

        private boolean isRegistered(char jobID) {
            for (Job next : jobs) {
                if (next.jobID == jobID) return true;
            }
            return false;
        }

        @Override
        public String sort() {
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

        private class Job implements Comparable {
            private final ArrayList<Job> dependencies = new ArrayList<>();
            private final char jobID;

            private Job(char jobID) {
                this.jobID = jobID;
            }

            private void addDependency(Job dependency) {
                this.dependencies.add(dependency);
            }


            @Override
            public int compareTo(@NotNull Object o) {
                if (((Job) o).jobID == this.jobID) return 0;

                int out = (this.jobID + getDependencyValue()) - (((Job) o).jobID + ((Job) o).getDependencyValue());
                if (out == 0) out = -1;
                return out;
            }

            private int getDependencyValue() {
                int out = 0;
                for (Job dependency : dependencies) {
                    if (dependency.dependencies.isEmpty()) return jobID;
                    out += dependency.getDependencyValue();
                }
                return out;
            }
        }
    }
}