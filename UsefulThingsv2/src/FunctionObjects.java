import java.util.Comparator;
import java.util.function.*;
import java.util.ArrayList;

// FUNCTION OBJECTS
// why use them?
// - they clean up code when you have complex, repeated processes

// predicates check a boolean condition
class PredExample<T> implements Predicate<Integer> {
  ArrayList<T> list;
  PredExample(ArrayList<T> list) {
    this.list = list;
  }
  
  public boolean test(Integer t) {
    return this.list.get(t).equals("foo");
  }
}

// functions take an input of type A and return an input of type R
class FuncExample implements Function<Integer, String> {
  public String apply(Integer i) {
    return i.toString();
  } 
}

// consumers modify the given object in some way
class ConsumExample<T> implements Consumer<ArrayList<T>> {
  public void accept(ArrayList<T> t) {
    for (T item : t) {
      t.indexOf(item); //yes this consumer is pretty pointless
    }
  }
}

// bifunctions take two inputs of the same or different types and 
// return an output of type R
class BiFuncExample implements BiFunction<Integer, String, Boolean> {
  public Boolean apply(Integer t, String u) {
    return t >= u.length();
  }
}

// comparators take in two values of type T and determine which one comes "first",
// output < 0: o1 first, out = 0: equal, out > 0, o2 first
class CompExample implements Comparator<String> {
  public int compare(String o1, String o2) {
    return o1.compareTo(o2);
  }
}