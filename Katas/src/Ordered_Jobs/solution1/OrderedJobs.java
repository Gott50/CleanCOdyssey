package Ordered_Jobs.solution1;

interface OrderedJobs {
    void register(char dependentJob, char independentJob);

    void register(char dependentJob);

    String sort();

    String sort(String registrations);

}
