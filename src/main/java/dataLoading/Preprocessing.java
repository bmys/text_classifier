package dataLoading;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Preprocessing {
    public static String removeNonLetterChars(String text){

        return text.replaceAll("[^A-Za-z]+", " ").trim();
    }

    public static List<String> removeStopWords(List<String> document, List<String> stopWords){
        document.removeAll(stopWords);
        return document;
    }

    public static List<String> loadStopWordsFromFile(String fileName){
        List<String> stopWords = new LinkedList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stopWords = stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stopWords;
    }
}
