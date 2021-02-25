interface ILoString {
  // ILoString -> ILoString
  // sorts this list according to some condition
  ILoString sort();
    // inserts the given string to the appropriate place in this list
    ILoString insert(String s);
  // filters this list according to a specified condition
  ILoString filter();
  
  // ILoString -> boolean
  // do all items in this list meet a certain condition?
  boolean andmap();
  //does an item in this list meet a certain condition?
  boolean ormap();
  
  // ILoString -> String
  // turns this list of strings into a single string
  String fold();
  // turns this list and the given list into a single list
  ILoString fold2(ILoString that);
}

class MtLoString implements ILoString {
  public ILoString sort() {
    return new MtLoString();
  }
  
  public ILoString insert(String s) {
    return new ConsLoString(s, new MtLoString());
  }

  public ILoString filter() {
    return new MtLoString();
  }

  public boolean andmap() {
    return true;
  }

  public boolean ormap() {
    return false;
  }

  public String fold() {
    return "";
  }

  public ILoString fold2(ILoString that) {
    return ;
  }
}

class ConsLoString implements ILoString {
  String first;
  ILoString rest;
  
  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public ILoString sort() {
    return this.rest.sort().insert(this.first);
  }
  
  public ILoString insert(String s) {
    if (/*s should be added to front*/) {
      return new ConsLoString(s, this);
    }
    else {
      return new ConsLoString(this.first, this.rest.insert(s));
    }
  }

  public ILoString filter() {
    if(/*this.first meets condition to be kept*/) {
      return new ConsLoString(this.first, this.rest.filter());
    }
    else {
      return this.rest.filter();
    }
  }

  public boolean andmap() {
    return /*whether this.first meets condition*/
        && this.rest.andmap();
  }

  public boolean ormap() {
    return /*whether this.first meets condition*/
        || this.rest.ormap();
  }

  public String fold() {
    return this.first + " " + this.rest.fold();
  }
  
  public ILoString fold2(ILoString that) {
    return new ConsLoString(this.first, that.fold2(this.rest));
  }
}
