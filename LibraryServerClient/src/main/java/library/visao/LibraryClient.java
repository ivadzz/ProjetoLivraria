package library.visao;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class LibraryClient {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Scanner scanner = new Scanner(System.in);
            String userInput;

            System.out.println("Conectado ao servidor da livraria.");
            System.out.println("Comandos disponiveis:");
            System.out.println("LIST");
            System.out.println("ADD <Nome do livro>, <Autor>, <Genero>, <numero de copias>");
            System.out.println("RENT <Nome do livro>");
            System.out.println("RETURN <Nome do livro>");
            System.out.println("Digite 'exit' para sair.");

            while (true) {
                System.out.print("> ");
                userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(userInput);
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                    if (!in.ready()) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
