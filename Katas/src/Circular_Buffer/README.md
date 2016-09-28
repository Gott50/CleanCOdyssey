#[Class Kata “Circular Buffer”](http://ccd-school.de/en/coding-dojo/classes-katas/circular-buffer/)


Write a class which implements a [circular buffer](http://en.wikipedia.org/wiki/Circular_buffer).

You can append new elements to a circular buffer at the end (**Add()**) like with a queue. And they can be extracted at the beginning (**Take()**), again like with a queue. But a circular buffer has a limited capacity (**Size()**) – and if that’s exhausted new elements will overwrite old elements. A circular buffer is wrapping around, it’s end and its head are connected.

The interface of the class should look like this:
	
    class CircularBuffer {
        CircularBuffer (int size) {...}
     
        void Add(T value) {...} 
        T Take() {...}
        int Count() {...} // Number of yet unread elements (<=Size())
        int Size() {...} // Total length of buffer
    }

Example usage:

| Action                    | Buffer content|
|---------------------------|---------------|
| new CircularBuffer<int>(3)|               |
| Add(1)                    | 1             |
| Add(2)                    | 1,2           |
| Size() -> 3               |               |
| Count() -> 2              |               |
| Take() -> 1               | 2             |
| Add(3)                    | 2,3           |
| Add(4)                    | 2,3,4         |
| Add(5)                    | 3,4,5         |
| Take() -> 3               | 4,5           |
| Add(6)                    | 4,5,6         |
| Add(7)                    | 5,6,7         |



by [Clean Code Developer School](http://ccd-school.de/)