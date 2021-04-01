// 1. non-generic list classes
// pros:
// - methods specific to given data can go in the interface
// cons:
// - obviously if multiple lists are being used in a single program it'll be
//   veeeery repetitive
// - can only access items by filtering thru entire list
interface IListOfInt { }
class MtListOfInt implements IListOfInt { }
class ConsListOfInt implements IListOfInt {
  int first;
  IListOfInt rest;
  ConsListOfInt(int first, IListOfInt rest) {
    this.first = first;
    this.rest = rest;
  }
}

// 2. generic list classes
// pros:
// - the abstracted version of the former
// cons:
// - all IList<T> methods must work with any type
// - still the (first, rest) structure
interface IListGen<T> { }
class MtListGen<T> implements IListGen<T> { }
class ConsListGen<T> implements IListGen<T> {
  T first;
  IListGen<T> rest;
  ConsListGen(T first, IListGen<T> rest) {
    this.first = first;
    this.rest = rest;
  }
}

// 3. home-grown mutable lists
// pros:
// - can add any methods we want
// - can more easily do things with the first value
// cons:
// - we need to deal with the internal functions
abstract class AMutableList<T> {
  Sentinel<T> sentinel;
  AMutableList(Sentinel<T> sentinel) {
    this.sentinel = sentinel;
  }
  
  //EFFECT: removes the given item from list (uses extensional equality)
   void remove(T t) {
     this.sentinel.removeHelp(t, this.sentinel);
   }
}
abstract class AList<T> { 
  // EFFECT: helper for remove()
  abstract void removeHelp(T t, ANode<T> prev);
}
class MtMutableList<T> extends AList<T> {
  // EFFECT: helper for remove()
  void removeHelp(T t, ANode<T> prev) {
    return;
  }
}
abstract class ANode<T> extends AList<T> {
  AList<T> rest;
  ANode(AList<T> rest) {
    this.rest = rest;
  }
}
class Sentinel<T> extends ANode<T> {
  //AList<T> rest;
  Sentinel(AList<T> rest) {
    super(rest);
  }

  // EFFECT: helper for remove()
  public void removeHelp(T t, ANode<T> prev) {
    this.rest.removeHelp(t, prev);
  }
}
class ConsMutableList<T> extends ANode<T> {
  T data;
  //AList<T> rest;
  ConsMutableList(T data, AList<T> rest) {
    super(rest);
    this.data = data;
  }
  
  // EFFECT: helper for remove()
  public void removeHelp(T t, ANode<T> prev) {
    if (this.data.equals(t)) {
      prev.rest = this.rest;
    } else {
      this.rest.removeHelp(t, this);
    }
  }
}

// 4. ArrayLists
// pros:
// - premade; we don't have to know how it functions structurally
// cons:
// - we can't add methods to the class; we have to make an ArrayUtils class
/*not really*/ abstract class ArrayList<T> {
  // returns the number of elements in the list
  abstract int size();
  // EFFECT: Adds the given value at the end of the list
  abstract boolean add(int index, T value);
  // Returns the value at the given index
  abstract T get(int index);
  // Returns the current value at the given index
  // EFFECT: Updates the value at the given index to the given new value
  abstract T set(int index, T newValue);
  // Returns the element that is removed
  // EFFECT: Removes the element at the specified position in this list.
  abstract T remove(int index);
}

// 5. deques
// pros:
// - can function like a stack or a mutable list
/*not really*/ abstract class Deque<T> {
  SentinelDeque<T> header;
  // Returns the number of items in this Deque (excluding the sentinel)
  abstract int size();
  // EFFECT: inserts the given value at the front of the Deque
  abstract void addToHead(T value);
  // EFFECT: inserts the given value at the back of the Deque
  abstract void addToTail(T value);
  // Returns the current first item of the Deque
  // EFFECT: removes the current first item of the Deque
  abstract T removeFromHead();
  // Returns the current last item of the Deque
  // EFFECT: removes the current last item of the Deque
  abstract T removeFromTail();
}
class ANodeDeque<T> {
  ANodeDeque<T> next;
  ANodeDeque<T> prev;
}
class SentinelDeque<T> extends ANodeDeque<T> { }
class NodeDeque<T> extends ANodeDeque<T> {
  T data;
}