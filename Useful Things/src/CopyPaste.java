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