#[Class Kata „Priority Queue“](http://ccd-school.de/en/coding-dojo/classes-katas/priority-queue/)


Write a class that implements a queue that holds a priority for each element. Elements with a high priority are placed in front of elements with a lower priority. Elements with the same priority are ordered in the order they where inserted into the queue.

The interface of the class has to look like the following:
	
    class PriorityQueue {
        void Enqueue(T element, int priority) {...}
        T Dequeue() {...}
        int Count() {...} // Number of elements in the Queue
    }


Example:

| Action                   | Queue                      |
|--------------------------|----------------------------|
| new PriorityQueue<int>() |                            |
| Enqueue(1, 5)            | (1,5)                      |
| Enqueue(2, 5)            | (1,5), (2,5)               |
| Dequeue() -> 1           | (2,5)                      |
| Enqueue(3, 7)            | (3,7), (2,5)               |
| Enqueue(4, 7)            | (3,7), (4,7), (2,5)        |
| Enqueue(5, 3)            | (3,7), (4,7), (2,5), (5,3) |
| Count() -> 4             |                            |


by [Clean Code Developer School](http://ccd-school.de/)