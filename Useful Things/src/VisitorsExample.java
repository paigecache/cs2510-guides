import java.util.function.Function;

import tester.Tester;

/////////////////////////////////////////////////////////////

interface IShape { 
  // To return the result of applying the given function to this shape
  <R> R accept(IShapeVisitor<R> visitor);
}

class Circle implements IShape {
  int x, y;
  int radius;
  String color;
  Circle(int x, int y, int r, String color) {
    this.x = x;
    this.y = y;
    this.radius = r;
    this.color = color;
  }
  
  // 2nd: apply calls the accept function and passes the visitor in
  //To return the result of applying the given visitor to this Circle
  public <R> R accept(IShapeVisitor<R> visitor) {
    return visitor.visitCircle(this);
  }
}

class Rect implements IShape {
  int x, y;
  double w, h;
  String color;
  Rect(int x, int y, double w, double h, String color) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.color = color;
  }
  
  // 2nd: apply calls the accept function and passes the visitor in
  //To return the result of applying the given visitor to this Circle
  public <R> R accept(IShapeVisitor<R> visitor) {
    return visitor.visitRect(this);
  }
}

interface IShapeVisitor<R> extends Function<IShape, R> {
  R visitCircle(Circle c);
  R visitRect(Rect r);
}

//A function object that computes the area of IShapes
class ShapeArea implements IShapeVisitor<Double>  {
  
  // 3rd: accept calls the appropriate function on given visitor, and
  // the desired function is performed + result returned
  public Double visitCircle(Circle c) {
    return Math.PI * c.radius * c.radius;
  }
  
  // 3rd: accept calls the appropriate function on given visitor, and
  // the desired function is performed + result returned
  public Double visitRect(Rect r) {
    return r.w * r.h;
  }
 
  // 1st: map calls the apply function
  public Double apply(IShape s) {
    return s.accept(this);
  }
}

//////////////////////////////////////////////////////////////

interface IList<A> {
  <R> IList<R> map(Function<A, R> func);
}

class MtList<A> implements IList<A> {
  public <R> IList<R> map(Function<A, R> func) {
    return new MtList<R>();
  }
}

class ConsList<A> implements IList<A> {
  A first;
  IList<A> rest;
  
  ConsList(A first, IList<A> rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public <R> IList<R> map(Function<A, R> func) {
    return new ConsList<R>(func.apply(this.first),
                           this.rest.map(func));
  }
}

//////////////////////////////////////////////////////////////

class ExamplesShapes {
  Circle circle = new Circle(10, 12, 4, "blue");
  Rect rect = new Rect(7, 10, 5.5, 3.9, "green");
  
  IList<IShape> shapes = new ConsList<>(circle,
                             new ConsList<>(rect, new MtList<>()));
  IList<Double> areas = new ConsList<>(50.265,
                            new ConsList<>(21.45, new MtList<>()));
  
  void testShapeArea(Tester t) {
    t.checkInexact(this.shapes.map(new ShapeArea()), this.areas, 0.001);
  }
}