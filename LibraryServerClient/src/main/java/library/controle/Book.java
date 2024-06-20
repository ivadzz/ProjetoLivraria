package library.controle;

public class Book {
    private String author;
    private String name;
    private String genre;
    private int numberOfCopies;

    public Book(String author, String name, String genre, int numberOfCopies) {
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.numberOfCopies = numberOfCopies;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}
