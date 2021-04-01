// say we have classes A and B; A is a field of B, and vice versa.
// the best way to initialize data with cyclical values is to start, say,
// B's A field as some null or empty value, and, in this case, to have A
// call a method on its B field which will *mutate* B, setting its A value
// to the given object

// see below for an example

class Author {
  String first;
  String last;
  int yob;
  IList<Book> books;
  
  Author(String fst, String lst, int yob) {
    this.first = fst;
    this.last = lst;
    this.yob = yob;
    // an Author object has an empty list of Books when initialized
    this.books = new MtList<Book>();
  }
  
  // this method gets called in the Book constructor
  // EFFECT: modifies this Author's list of Books to refer to the given Book
  void addBook(Book b) {
    if (!b.author.sameAuthor(this)) {
      throw new RuntimeException("book was not written by this author");
    }
    else {
      this.books = new ConsList<Book>(b, this.books);
    }
  }

  boolean sameAuthor(Author author) {
    return this.first.equals(author.first)
        && this.last.equals(author.last);
  }
}

class Book {
  String title;
  int price;
  int quantity;
  Author author;
  
  Book(String title, int price, int quantity, Author ath) {
    this.title = title;
    this.price = price;
    this.quantity = quantity;
    this.author = ath;
    // this method mutates the author's list of Books to add this book
    this.author.addBook(this);
  }
}