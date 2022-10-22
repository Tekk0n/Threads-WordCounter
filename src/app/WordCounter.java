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

            while (reader.ready()) {
                line = br.readLine();
                counter = line.split(" ");
                //Evita contar saltos de linea como una palabra
                if (!line.equals("")) {
                    count += counter.length;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File " + _text.getFileName() + " has " + count);
    }

}

