#[Class Kata “Stack”](http://ccd-school.de/en/coding-dojo/classes-katas/stack/)

Implement the abstract data type Stack. The stack is a first-in last-out data structure. Elements that are put on the stack are returned in reverse order. See the example below for details.

The interface has to look like the following:
public interface IStack
	
    public interface IStack
    {
        void Push(TElement element);
     
        TElement Pop();
    }

The data type has to be implemented as a generic data type where TElement is the type of the elements. The stack has two operations, Push and Pop. Push puts an element on the top of the stack whereas Pop returns the topmost element and removes it from the stack. If Pop is called on an empty stack an IllegalOperationException is thrown.

Example:

| Stack | Operation | Result    |
|-------|-----------|-----------|
| Empty | Push(5)   | -         |
| 5     | Pop()     | 5         |
| Empty | Pop()     | Exception |
| Empty | Push(7)   | -         |
| 7     | Push(9)   | -         |
| 7,9   | Push(1)   | -         |
| 7,9,1 | Pop()     | 1         |
| 7,9   | Pop()     | 9         |
| 7     | Pop()     | 7         |
| Empty |           |           |
	

##Variation

Try to write automated tests for the Push operation that does not use the Pop operation. Furthermore write tests for Pop that don’t use Push.

It should be clear that there have to be integration tests that demonstrate that Push and Pop work together as expected. Nonetheless it is a good idea to have tests that are focused and test each operation in isolation. It is easier this way to see which operation leads to a false test. Furthermore one can implement both operations in any order and independently.


by [Clean Code Developer School](http://ccd-school.de/)