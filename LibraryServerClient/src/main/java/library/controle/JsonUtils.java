package library.controle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static Library loadLibrary(String filename) {
        try (Reader reader = new FileReader(filename)) {
            Type libraryType = new TypeToken<Library>() {}.getType();
            return gson.fromJson(reader, libraryType);
        } catch (IOException e) {
            e.printStackTrace();
            return new Library();
        }
    }

    public static void saveLibrary(Library library) {
        try (Writer writer = new FileWriter("books.json")) {
            gson.toJson(library, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
