package java8.iterator;

public class Main {

    public static void main(String[] args) {
        Bookshelf bookshelf = new Bookshelf(6);
        bookshelf.addBook(new Book(1,"赎罪曲"));
        bookshelf.addBook(new Book(2,"赎罪曲"));
        bookshelf.addBook(new Book(3,"赎罪曲"));
        bookshelf.addBook(new Book(4,"赎罪曲"));
        bookshelf.addBook(new Book(5,"赎罪曲"));
        bookshelf.addBook(new Book(6,"赎罪曲"));
        MyIterator iterator = bookshelf.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }

    }
}
