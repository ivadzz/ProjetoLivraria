package library.controle;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void rentBook(String bookName) {
        for (Book book : books) {
            if (book.getName().equals(bookName) && book.getNumberOfCopies() > 0) {
                book.setNumberOfCopies(book.getNumberOfCopies() - 1);
                break;
            }
        }
    }

    public void returnBook(String bookName) {
        for (Book book : books) {
            if (book.getName().equals(bookName)) {
                book.setNumberOfCopies(book.getNumberOfCopies() + 1);
                break;
            }
        }
    }
}

