package java8.iterator;

public class Bookshelf implements Assemble{
    private Book[] books;
    private int index;

    public Bookshelf(int len) {
        this.books = new Book[len];
        this.index = 0;
    }

    public Integer addBook(Book book){
        books[index] = book;
        index += 1;
        return index;
    }

    public Book getBook(int index){
        return books[index];
    }

    public int getLen(){
        return index;
    }
    @Override
    public MyIterator iterator() {
        return new BookIterator(this);
    }
}
