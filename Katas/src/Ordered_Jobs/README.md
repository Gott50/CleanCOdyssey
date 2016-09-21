#[Function Kata „Ordered Jobs“](http://ccd-school.de/en/coding-dojo/classes-katas/ordered-jobs/)



Develop a class which calculates a plan to execute jobs depending on each other [1].

Each job is represented by a single character, e.g. ‘c’ or ‘x’.

If job ‘a’ depends on job ‘u’ to be finished first then that can be expressed as a=>u or (a,u). Each job can depend on any number of other jobs, which in turn can depend on other jobs etc.

After a number of job have been registered with their dependencies the class should order them from least dependent/independent to most dependent.

The interface for this should look like this:

    interface IOrderedJobs {
      void Register(char dependentJob, char independentJob);
      void Register(char job);
     
      string Sort();
    }

If used like this:
	
    Register('c');
    Register('b', 'a');
    Register('c', 'b');

…_Sort()_ returns:
	
    "abc"

Jobs which get registered several times of course only appear once in the execution plan.

Independent jobs can appear at any point in the execution plan, as long as they get listed before any jobs depending on them.

Direct or indirect circular dependencies should be flagged by throwing an exception  – this can be done upon registration or sorting.

##Variations #1

Implement an alternative function _string Sort(string registrierungen)_ which accepts job registrations as a multi-line text of this form [1]:
	
    c =>
    b => a
    c => b

##Resources

[1]The Ordered Jobs Kata, http://invalidcast.com/2011/09/the-ordered-jobs-kata

by [Clean Code Developer School](http://ccd-school.de/)