import java.util.ArrayList;
import java.util.function.*;

// for-each loops:
// - CANNOT MODIFY OBJECT BEING ITERATED OVER
// - automatically exist for ArrayLists, need to make an Iterator (and an Iterable)
//   to use on other things
// - made to do something to each item in a list
// - "If a problem can be expressed as a computation over the particular items of an ArrayList, use a for-each loop. "

// default structure:
//... myMethod(ArrayList<X> xs) {
//  ...setup...
//  for (X x : xs) {
//    ...body...
//  }
//  ...use results...
//}

// (counted-)for loops:
// - can modify the value being used to iterate within the loop
// - index-based when it comes to lists
// - not exclusive to lists
// - "If the problem requires specifically manipulating indices, or counting for whatever reason, use a counted-for loop."

// structure:
// for (int count = 0;       -> (1) initialization statement: declares the loop variable + initializes it to its starting value
//      count < n;           -> (2) termination condition: checked before every iteration of loop body; terminates loop when false
//      count = count + 1) { -> (4) update statement: executed after each loop body, advances loop variable
//  return count;            -> (3) loop body: executed every iteration of the loop
//}

// while loops:
// - aren't guaranteed to run: if condition is never true loop is skipped
// - useful when you don't need to keep track of iterations
// - "If the number of iterations of the loop is not known a priori, use a while loop."

// structure:
//while (someBooleanExpression()) {
//  ...body of loop...
//}

class ArrayUtils {
  // for-each loop
  <T, U> ArrayList<U> map(ArrayList<T> arr, Function<T, U> func) {
    ArrayList<U> result = new ArrayList<U>();
    for (T t : arr) {
      result.add(func.apply(t));
    }
    return result;
  }
  
  // counted-for loop
  <U> ArrayList<U> buildList(int n, Function<Integer, U> func) {
    ArrayList<U> result = new ArrayList<U>();
    for (int i = 0; i < n; i = i + 1) {
      result.add(func.apply(i));
    }
    return result;
  }
  
  // while loop
  int foldArrayListInt(ArrayList<Integer> arr) {
    int ans = 0;
    while (arr.size() > 0) {
      ans = ans + arr.remove(0);
    }
    return ans;
  }
}