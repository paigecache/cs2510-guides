import tester.Tester;

// interfaces represent union data, and they serve as a "series of promises" (thanks blerner)
// that each class which implements the interface must fulfill
// a.k.a. if you put a method here put it everywhere else
interface IClass{
  // adds all ints that this IClass has access to
  int addAllInts();
  
  // generates a string specific to each class
  String generateString();
}

// abstract classes allow you to eliminate repeated code
abstract class AClass implements IClass {
  int sharedInt;
  int sharedInt2;
  String sharedString;
  
  AClass(int sharedInt, int sharedInt2, String sharedString) {
    this.sharedInt = sharedInt;
    this.sharedInt2 = sharedInt2;
    this.sharedString = sharedString;
  }
  
  // this method will be inherited by all of the below classes
  public int addAllInts() {
    return this.sharedInt + this.sharedInt2;
  }
  
  // abstract methods must be defined in all subclasses
  public abstract String generateString();
}

class Class1 extends AClass {
  boolean c1Bool;
  
  Class1(int c1Int, int c1Int2, String c1String, boolean c1Bool) {
    //super just means use the constructor in my superclass to define these
    super(c1Int, c1Int2, c1String);
    // c1Int is defined under this.sharedInt, c2Int is defined under this.sharedInt2, and
    // c1String is defined under this.sharedString
    
    this.c1Bool = c1Bool;
  }
  
  // inherits addAllInts from AClass
  
  public String generateString() {
    return this.sharedString;
  }
}

// subclasses are an extension of a class; they inherit the same fields and methods as the
// previous class (and whatever else is above it), and they can either override the methods
// or use the ones they inherit
class Subclass extends Class1 {
  int scInt;
  
  Subclass(int scInt, int scInt2, String scString, boolean scBool, int scInt3) {
    super(scInt, scInt2, scString, scBool);
    // parameters are represented by same fields as in Class1 (except scBool is this.c1Bool)
    
    this.scInt = scInt3;
  }
  
  // this method overrides addAllInts() found in the abstract class
  public int addAllInts() {
    return super.addAllInts() //super.addAllInts() returns output of the abstract addAllInts
        + this.scInt;
  }
  
  // inherits generateString from Class1
}

class Class2 extends AClass{
  String c2String;
  
  Class2(int c2Int, int c2Int2, String c2String, String c2String2) {
    super(c2Int, c2Int2, c2String);
    this.c2String = c2String2;
  }
  
  // inherits addAllInts from AClass
  
  public String generateString() {
    return this.sharedString + " " + this.c2String;
  }
}

class ExamplesClass {
  IClass class1 = new Class1(3, 5, "hi", true);
  IClass subclass = new Subclass(4, 2, "yo", false, 8);
  IClass class2 = new Class2(1, 9, "yay", "woo");
  
  boolean testAddAllInts(Tester t) {
    return t.checkExpect(this.class1.addAllInts(), 8)
        && t.checkExpect(this.subclass.addAllInts(), 14)
        && t.checkExpect(this.class2.addAllInts(), 10);
  }
  
  boolean testGenerateString(Tester t) {
    return t.checkExpect(this.class1.generateString(), "hi")
        && t.checkExpect(this.subclass.generateString(), "yo")
        && t.checkExpect(this.class2.generateString(), "yay woo");
  }
}