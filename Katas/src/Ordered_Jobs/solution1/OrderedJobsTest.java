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

// --Commented out by Inspection START (24/09/16 17:45):
//        private Job getJob(char jobID) {
//            if (jobs.removeIf(job -> job.jobID == jobID))
//                return registerJob(jobID);
//            else
//                return null;
//        }
// --Commented out by Inspection STOP (24/09/16 17:45)


        @Override
        public void register(char jobID) {
            Job job = new Job(jobID);
            jobs.add(job);
        }

        private Job registerJob(char jobID) {
            Job job = new Job(jobID);
            jobs.add(job);
            return job;
            //else return this.getJob(jobID);
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

                return (this.jobID + getDependencyValue()) - (((Job) o).jobID + ((Job) o).getDependencyValue());
            }

            private int getDependencyValue() {
                int out = 0;
                for (Job dependency : dependencies) {
                    if (dependency.dependencies.isEmpty()) return 1;
                    out += dependency.getDependencyValue();
                }
                return out;
            }
        }
    }
}