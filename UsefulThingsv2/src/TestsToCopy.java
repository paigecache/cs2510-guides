import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

class ExamplesUsefulTests {
  ArrayList<Integer> copyThis = new ArrayList<Integer>(
      Arrays.asList(1, 2, 3, 4, 5));
  
  int method() {
    return 2;
  }
  
  void exCheckExpect (Tester t) {
    t.checkExpect(this.method(), 2);
  }
  
  void exCheckInexact (Tester t) {
    t.checkInexact(this.method(), 2, 0.01);
  }

  void exCheckException (Tester t) {
    t.checkException(
        new Exception("Error message"), //exception is specific to what you're testing
        this, //object method is being called on
        "methodNameAsString",
        "arg1", "arg2"); //any arguments the method takes in, put nothing if none
  }
  
  void exConstructorException (Tester t) {
    t.checkConstructorException(
        new IllegalArgumentException("Error message"),
        "ClassName",
        "arg1", "arg2"); //constructor args
  }
}