import tester.Tester;

/* Sameness is quantified by these properties:
 * 
 * - Reflexivity: every object should be the same as itself.
 * - Symmetry: if object x is the same as object y, then y is the same as x.
 * - Transitivity: if two objects are both the same as a third object, then they are the same as each other.
 * - Totality: we can compare any two objects of the same type, and obtain a correct answer.
 */

/*
 * Extensional equality: two items are extensionally equal if their fields are the same: 
 * either they’re equal primitive values, or they’re objects that are themselves extensionally equal.
 * - checked with .equals()
 * Intensional equality: two items are intensionally equal if they are the exact same object.
 * - checked with ==
 * - aliased objects are intensionally equal
 * 
 * hash codes and equality:
 * - If we override equals such that objA.equals(objB) is true, then we must
 *   also override hashCode to ensure objA.hashCode() == objB.hashCode().
 * - If we override equals such that objA.equals(objB) is false, then 
 *   objA.hashCode() and objB.hashCode() may or may not be the same.
 * - If we override hashCode such that objA.hashCode() != objB.hashCode(), 
 *   then we must also override equals to ensure objA.equals(objB) is false.
 * - If we override hashCode such that objA.hashCode() == objB.hashCode(), 
 *   then objA.equals(objB) may or may not be true.
 */

class Sameness {
  String string1;
  int int2;
  
  Sameness(String string1, int int2) {
    this.string1 = string1;
    this.int2 = int2;
  }
  
  boolean comparingPrimitives(Tester t) {
    return //integers
           t.checkExpect(3 == 4, false)
        //&& t.checkExpect(4 == 4, true)
           //booleans
        && t.checkExpect(true == false, false)
        //&& t.checkExpect(true == (5 > 3), true)
           //Strings
        && t.checkExpect("hello".equals("hello"), true)
        && t.checkExpect("hello".equals("hi"), false)
           //doubles
        && t.checkExpect(4.333 - 4.333 < 0.001, true) //absolute difference
        && t.checkInexact(4.333, 4.333, 0.01); //relative difference
  }
  
  boolean sameObject(Sameness that){
    return this.string1.equals(that.string1)
        && this.int2 == that.int2;
  }
}


// Sameness of Union Data; Double Dispatch
interface IFoo {
  boolean sameFoo(IFoo that);
 
  boolean sameX(X that);
  boolean sameY(Y that);
  boolean sameZ(Z that);
}

// use an abstract class to eliminate the repetition of "return false;"
// the sameClass methods should return false everywhere that the object called upon
// is not of the same type as the host class

abstract class AFoo implements IFoo {
  // is this IFoo the same as that X?
  public boolean sameX(X that) { return false; }
  
  // is this IFoo the same as that Y?
  public boolean sameY(Y that) { return false; }
  
  // is this IFoo the same as that Z?
  public boolean sameZ(Z that) { return false; }

  // allows us to use .equals on IFoo
  public boolean equals(Object other) {
    if (!(other instanceof IFoo)) { return false; }
    // this cast is safe, because we just checked instanceof
    IFoo that = (IFoo)other;
    return this.sameFoo(that);
}
}

class X extends AFoo {
  int int1;
  X(int int1) {
    this.int1 = int1;
  }
  
  // is this X the same as that IFoo?
  public boolean sameFoo(IFoo that) {
    // DOUBLE DISPATCH
    // since we know that this is an X, we can have sameX called on that;
    // if that is an X it will compare their fields, if not it will return false (see abstract)
    return that.sameX(this);
  }
  
  // is this X the same as that X?
  public boolean sameX(X that) {
    return this.int1 == that.int1;
  }
}

class Y extends AFoo {
  String string1;
  Y(String string1) {
    this.string1 = string1;
  }
  
  // is this Y the same as that IFoo?
  public boolean sameFoo(IFoo that) {
    return that.sameY(this);
  }
  
  // is this Y the same as that Y?
  public boolean sameY(Y that) {
    return this.string1.equals(that.string1);
  }
}

class Z extends AFoo {
  Y object1;
  Z(Y object1) {
    this.object1 = object1;
  }
  
  // is this Z the same as that IFoo?
  public boolean sameFoo(IFoo that) {
    return that.sameZ(this);
  }
  
  // is this Z the same as that Z?
  public boolean sameZ(Z that) {
    return this.object1.sameY(that.object1);
  }
}