package library.visao;

import library.controle.JsonUtils;
import library.controle.Library;
import library.controle.LibraryHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LibraryServer {
    private static final int PORT = 12345;
    private final Library library;

    public LibraryServer() {
        library = JsonUtils.loadLibrary("books.json");
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Library server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                new LibraryHandler(clientSocket, library).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LibraryServer().start();
    }
}
