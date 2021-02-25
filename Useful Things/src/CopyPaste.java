import tester.*;

class Templates {
  /* TEMPLATE:
   * FIELDS:
   * this.
   * 
   * METHODS:
   * this.
   * 
   * METHODS ON FIELDS:
   * this.
   */
  
  int methodTemplate() {
    /* TEMPLATE:
     * PARAMETERS:
     * 
     * METHODS ON PARAMETERS:
     * 
     */
    return 2;
  }
}

//tester class copy paste
class ExamplesClassName {
  //examples
  Templates ex1 = new Templates();

  boolean testMethodTemplate(Tester t) {
    return t.checkExpect(this.ex1.methodTemplate(), 2)
        && t.checkExpect(this.ex1.methodTemplate(), 2)
        && t.checkExpect(this.ex1.methodTemplate(), 2);
   }

  boolean testDoubleMethod(Tester t) {
    return t.checkInexact(this.ex1.methodTemplate(), 2, 0.01)
        && t.checkInexact(this.ex1.methodTemplate(), 2, 0.01)
        && t.checkInexact(this.ex1.methodTemplate(), 2, 0.01);
  }

  boolean checkConstructorException(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException("Error message"),
                                       "ClassName",
                                       "arg1", "arg2") //constructor args
        && t.checkConstructorException(new IllegalArgumentException("Error message"),
                                       "ClassName",
                                       "arg1", "arg2")
        && t.checkConstructorException(new IllegalArgumentException("Error message"),
                                       "ClassName",
                                       "arg1", "arg2");
  }
}

// ty abijit

//represents a list of 
interface ILo {
  // methods go here
}

//represents an empty list of 
class MtLo implements ILo {
  MtLo() {}

  // methods go here
}


//represent a non-empty list of 
class ConsLo implements ILo {
  X first;
  ILo rest;

  ConsLo(X first, ILo rest) {
    this.first = first;
   this.rest = rest;
  }

// methods go here
  
}