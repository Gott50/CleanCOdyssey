#[Class Kata “Linked List”](http://ccd-school.de/en/coding-dojo/classes-katas/linked-list/)

Implement the abstract data type _list_ as a linked list. The class _LinkedList<T>_ has to implement the interface _IList<T>_.

A linked list consists of elements that have a value (called _Item_) and a reference to the next element in the list (called _Next_):

	
    class Element
    {
        public Element(T item) {
            Item = item;
        }
     
        public T Item { get; set; }
     
        public Element Next { get; set; }
    }

The list is internally built from these elements. But the public API does not show anything of this implementation details. The _LinkedList<T>_ behaves as other classes the implement the interface _IList<T>_:

	
    class LinkedList : IList {
        ...
    }

##Variation

The list may be double linked. In addition to the _Next_ property each element has a _Prev_ property that references the previous element in the list. This fastens the reverse traversal of the list from the last element backwards tot he first.
    
        
    class Element
    {
        public Element(T item) {
            Item = item;
        }
     
        public T Item { get; set; }
     
        public Element Next { get; set; }
     
        public Element Prev { get; set; }
    }



by [Clean Code Developer School](http://ccd-school.de/)