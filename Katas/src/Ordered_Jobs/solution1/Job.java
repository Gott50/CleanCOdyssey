package Ordered_Jobs.solution1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class Job implements Comparable {
    private final ArrayList<Job> dependencies = new ArrayList<>();
    private final char jobID;

    Job(char jobID) {
        this.jobID = jobID;
    }

    char getJobID() {
        return jobID;
    }

    void addDependency(Job dependency) {
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
