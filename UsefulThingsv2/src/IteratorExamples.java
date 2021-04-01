import java.util.Iterator;
import java.util.NoSuchElementException;
import tester.*;
import java.util.ArrayList;
import java.util.Arrays;

// iterator for not a list
class StringCharIterator implements Iterator<Character> {
  StringCharIterable sci;
  int count;
  
  StringCharIterator (StringCharIterable sci) {
    this.sci = sci;
    this.count = 0;
  }

  // does this iterator have a next value?
  public boolean hasNext() {
    return this.count < this.sci.str.length();
  }

  // get the next value of this iterator
  public Character next() {
    if (!this.hasNext()) {  
      throw new NoSuchElementException(); 
    }
    Character curChar = this.sci.str.charAt(count);
    this.count = this.count + 1;
    return curChar;
  }
}

class StringCharIterable implements Iterable<Character> {
  String str;
  
  StringCharIterable(String str) {
    this.str = str;
  }
  
  // iterator for a string
  public Iterator<Character> iterator() {
    return new StringCharIterator(this);
  }
  
  ArrayList<Character> listChars() {
    ArrayList<Character> ans = new ArrayList<Character>();
    for (Character c : this) {
      ans.add(c);
    }
    return ans;
  }
}

class ExamplesSCI {
  StringCharIterable test = new StringCharIterable("im dying");
  ArrayList<Character> list = new ArrayList<Character>(
      Arrays.asList('i', 'm', ' ', 'd', 'y', 'i', 'n', 'g'));
  
  void testListChars(Tester t) {
    t.checkExpect(this.test.listChars(), this.list);
  }
  
  void testHasNext(Tester t) {
    t.checkExpect(new StringCharIterable("").iterator().hasNext(), false);
    t.checkExpect(new StringCharIterable("ng").iterator().hasNext(), true);
  }
  
  void testNext(Tester t) {
    t.checkExpect(new StringCharIterable("hello").iterator().next(), 'h');
    t.checkExpect(new StringCharIterable("lo").iterator().next(), 'l');
    // <T> boolean checkException(Exception e, T target, String method, Object... args);
    t.checkException(new NoSuchElementException(), new StringCharIterable("").iterator(), "next");
  }
}

// higher-order iterator
class EveryOther<T> implements Iterator<T> {
  Iterator<T> source;
  EveryOther(Iterator<T> source) {
    this.source = source;
  }
  public boolean hasNext() {
    return this.source.hasNext();
  }
  public T next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }
    T answer = this.source.next(); 
    if (this.source.hasNext()) {
      this.source.next(); 
    }
    return answer;
  }
}

class EveryOtherIterable implements Iterable<Character> {
  StringCharIterator iter;
  EveryOtherIterable(StringCharIterator iter) {
    this.iter = iter;
  }
  
  public Iterator<Character> iterator() {
    return new EveryOther<Character>(this.iter);
  }
  
  ArrayList<Character> everyOtherChar() {
    ArrayList<Character> ans = new ArrayList<Character>();
    for (Character c : this) {
      ans.add(c);
    }
    return ans;
  }
}

class ExamplesEveryOther {
  EveryOtherIterable iter = 
      new EveryOtherIterable(new StringCharIterator(new StringCharIterable("im dying")));
  ArrayList<Character> list = new ArrayList<Character>(
      Arrays.asList('i', ' ', 'y', 'n'));
  
  void testIterator(Tester t) {
    t.checkExpect(this.iter.everyOtherChar(), this.list);
  }
  
  void testHasNext(Tester t) {
    t.checkExpect(
        new EveryOtherIterable(new StringCharIterator(new StringCharIterable(""))).iterator().hasNext(), false);
    t.checkExpect(
        new EveryOtherIterable(new StringCharIterator(new StringCharIterable("ng"))).iterator().hasNext(), true);
  }
  
  void testNext(Tester t) {
    EveryOtherIterable nextTest = new EveryOtherIterable(new StringCharIterator(new StringCharIterable("hello")));
    
    t.checkExpect(nextTest.iterator().next(), 'h');
    t.checkExpect(nextTest.iterator().next(), 'l');
    t.checkExpect(nextTest.iterator().next(), 'o');
    // <T> boolean checkException(Exception e, T target, String method, Object... args);
    t.checkException(new NoSuchElementException(), nextTest.iterator(), "next");
  }
}