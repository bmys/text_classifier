import java.util.ArrayList;

public class Preprocessing {
    public static String removeNonLetterChars(String text){

        return text.replaceAll("[^A-Za-z]+", " ").trim();
    }

    public static String removeStopWords(String text, ArrayList<String> stopWords){
        return "";
    }
}
