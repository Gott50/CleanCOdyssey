#[Class Kata “Bounded Queue”](http://ccd-school.de/en/coding-dojo/classes-katas/bounded-queue/)

Write a queue class of limited length for communication between different threads.

Reading threads can take elements from the queue – but if its empty reading is blocked until another element becomes available.

Writing threads add elements to the queue. In case the queue length has reached its limit, though, writing blocks until an element has been removed by a reading thread.

The interface of the class should look like this:

    class BoundedQueue {
        BoundedQueue(int size) {...}
        void Enqueue(T element) {...}
        T Dequeue() {...}
        int Count() {...} // Current number of elements in queue
        int Size() {...} // Max. number of elements
    }

Example:

| WritingThread             | Queue | Reading Thread    |
|---------------------------|-------|-------------------|
| new BoundedQueue<int>(2)  |       |                   |
| Enqueue(1)                | 1     |                   |
|                           |       | Dequeue() -> 1    |
| Enqueue(2)                | 2     |                   |
| Enqueue(3)                | 2,3   |                   |
| Enqueue(4) // blocks      |       |                   |
|                           | 3     |  Dequeue() -> 2   |
| // is released            | 3,4   |                   |
|                           | 4     | Dequeue() -> 3    |
|                           |       | Dequeue() -> 4    |
|                           |       |Dequeue() // blocks|
| Enqueue(5)                | 5     |                   |
|                           |       |-> 5 // is released|


Performance issues can be neglected.

##Variations #1

Add two functions to the class:

    class BoundedQueue {
        ...
        bool TryEnqueue(T element, int timeoutMsec) {...}
        bool TryDequeue(int timeoutMsec, out T element) {...}
    }

Reading/writing optionally should block only for a limited time. If the function succeeds before timeout, **true** is returned, otherwise **false**.


by [Clean Code Developer School](http://ccd-school.de/)