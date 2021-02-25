/*
interface ILoString {
  // ILoString -> ILoString
  ILoString sort();
  ILoString filter();
  
  // ILoString -> String
  String fold();
  
  // ILoString -> boolean
  boolean andmap();
  boolean ormap();
}

class MtLoString implements ILoString {
  
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;
  
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public ILoString sort() {
    
  }
}
*/