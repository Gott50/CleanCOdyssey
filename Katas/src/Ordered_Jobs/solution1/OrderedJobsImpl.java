package Ordered_Jobs.solution1;

import java.util.TreeSet;

class OrderedJobsImpl implements OrderedJobs {
    private final TreeSet<Job> jobs = new TreeSet<>();

    @Override
    public void register(char dependentJob, char independentJob) {
        Job dependency = registerJob(independentJob);
        registerJob(dependentJob).addDependency(dependency);
    }

    @Override
    public void register(char jobID) {
        registerJob(jobID);
    }

    @Override
    public String sort() {
        String out = "";
        for (Job job : jobs) {
            out += job.getJobID();
        }
        return out;
    }

    @Override
    public String sort(String registrations) {
        String[] lines = registrations.split("\n");
        for (String line : lines) {
            String[] jobs = line.split("=>");
            if (jobs.length == 2)
                register(jobStringToChar(jobs[0]), jobStringToChar(jobs[1]));
            else
                register(jobStringToChar(jobs[0]));
        }
        return sort();
    }

    private Job getJob(char jobID) {
        for (Job element : jobs) {
            if (element.getJobID() == jobID) return element;
        }
        return null;
    }

    private Job registerJob(char jobID) {
        Job job = new Job(jobID);
        if (!isRegistered(jobID) && jobs.add(job))
            return job;
        else return this.getJob(jobID);
    }

    private boolean isRegistered(char jobID) {
        for (Job next : jobs) {
            if (next.getJobID() == jobID) return true;
        }
        return false;
    }

    private char jobStringToChar(String jobString) {
        return jobString.trim().charAt(0);
    }

}
