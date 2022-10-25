package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class WordCounter extends Thread {
    private final Path _text;

    WordCounter(Path text) {
        _text = text;
    }

    @Override
    public void run() {
        String line;
        String[] counter;
        int count = 0;

        try (FileReader reader = new FileReader(String.valueOf(_text))) {
            BufferedReader br = new BufferedReader(reader);

            line = br.readLine();
            while (line != null) {
                counter = line.split(" ");
                count += counter.length;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nFile " + _text.getFileName() + " has " + count);
    }

}

