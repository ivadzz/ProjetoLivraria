package library.controle;

import java.io.*;
import java.net.Socket;

public class LibraryHandler extends Thread {
    private final Socket socket;
    private final Library library;

    public LibraryHandler(Socket socket, Library library) {
        this.socket = socket;
        this.library = library;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request;
            while ((request = in.readLine()) != null) {
                if (request.startsWith("LIST")) {
                    handleList(out);
                } else if (request.startsWith("ADD")) {
                    handleAdd(request, out);
                } else if (request.startsWith("RENT")) {
                    handleRent(request, out);
                } else if (request.startsWith("RETURN")) {
                    handleReturn(request, out);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleList(PrintWriter out) {
        for (Book book : library.getBooks()) {
            out.println("Author: " + book.getAuthor() + ", Name: " + book.getName() + ", Genre: " + book.getGenre() + ", Number of Copies: " + book.getNumberOfCopies());
        }
    }

    private void handleAdd(String request, PrintWriter out) {
        String[] parts = request.split(", ");
        if (parts.length == 4) {
            try {
                String name = parts[0].substring(4).trim(); 
                String author = parts[1].trim();
                String genre = parts[2].trim();
                int numberOfCopies = Integer.parseInt(parts[3].trim());

                Book newBook = new Book(author, name, genre, numberOfCopies);
                library.addBook(newBook);
                JsonUtils.saveLibrary(library);
                out.println("Livro adicionado com sucesso.");
            } catch (NumberFormatException e) {
                out.println("Erro: Numero de copias deve ser inteiro.");
            }
        } else {
            out.println("Erro: Comando invalido");
        }
    }

    private void handleRent(String request, PrintWriter out) {
        String bookName = request.substring(5).trim(); 
        boolean bookFound = false;

        for (Book book : library.getBooks()) {
            if (book.getName().equals(bookName)) {
                bookFound = true;
                if (book.getNumberOfCopies() > 0) {
                    book.setNumberOfCopies(book.getNumberOfCopies() - 1);
                    JsonUtils.saveLibrary(library);
                    out.println("Livro alugado com sucesso.");
                } else {
                    out.println("Erro: Sem copias disponiveis.");
                }
                break;
            }
        }

        if (!bookFound) {
            out.println("Erro: Livro nao encontrado.");
        }
    }

    private void handleReturn(String request, PrintWriter out) {
        String bookName = request.substring(7).trim(); 
        boolean bookFound = false;

        for (Book book : library.getBooks()) {
            if (book.getName().equals(bookName)) {
                bookFound = true;
                book.setNumberOfCopies(book.getNumberOfCopies() + 1);
                JsonUtils.saveLibrary(library);
                out.println("Livro retornado com sucesso.");
                break;
            }
        }

        if (!bookFound) {
            out.println("Erro: Livro nao encontrado.");
        }
    }
}
