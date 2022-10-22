package app;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        int numberOfTexts = 0;

        //Define path al directorio de los documentos
        Path filePath = Paths.get(System.getProperty("user.dir"), "Ejercicio1.WordCounter", "texts");
        System.out.println(filePath);

        //Reconoce y lista los ficheros y subdirectorios
        try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(filePath)) {
            for (Path stream : fileStream) {
                System.out.println(stream.getFileName());
                numberOfTexts++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Textos a traducir: " + numberOfTexts);
        System.out.println("Contando palabras...");

        //¿Es realmente concurrente?
        try (DirectoryStream<Path> fileStream = Files.newDirectoryStream(filePath)) {
            for (Path stream : fileStream) {
                int i = 1;
                Thread wordCounter = new WordCounter(stream);
                wordCounter.start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
