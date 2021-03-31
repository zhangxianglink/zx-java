package java8.iterator;

public class BookIterator implements  MyIterator{

    private Bookshelf bookshelf;
    private int index;

    public BookIterator(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
        this.index = 0;
    }


    @Override
    public boolean hasNext() {
        if (index < bookshelf.getLen()){
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        Book book = bookshelf.getBook(index);
        index += 1;
        return book;
    }
}
