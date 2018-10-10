/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
  int size;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
    size = 0;
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
  public boolean insert(double elt, int index) {
	if (index > size || index < 0) return false;
	Node nd = new Node(elt);
	if(sentinel.next == null) {
	  sentinel.next = nd;
	  sentinel.prev = nd;
      nd.next = sentinel;
	  nd.prev = sentinel;
	  size++;
	  return true;
	}
	int count = 0;
	Node curr = sentinel.next;
	while (count < index) {curr = curr.next; count++;}
	Node c = new Node(elt);
	c.prev = curr.prev;
	curr.prev = c;
	c.prev.next = c;
	c.next = curr;
	size++;
	return true;
  }

@Override
  public boolean remove(int index) {
	if (size == 0 || index < 0 || index >= size) return false;
	int count = 0;
	Node curr = sentinel.next;
	while (count < index) {
		curr = curr.next; count++;
	}
	curr.next.prev = curr.prev;
	curr.prev.next = curr.next;
	size--;
	return true;
  } 

@Override
  public double get(int index) {
	if (index < 0 || index >= size) return Double.NaN; 
	int count = 0;
	Node curr = sentinel.next;
	while (count < index) {
		curr = curr.next; count++;
	}
	return curr.data;
  }

@Override
  public int size() {
	  return size;
  }

@Override
  public boolean isEmpty() {
	  return (size == 0);
  }

@Override
  public void clear() {
    sentinel.next = null;
    sentinel.prev = null;
    size = 0;
  }
}