package Utility;

import Model.Corpus;
import ca.rmen.porterstemmer.PorterStemmer;

import java.util.List;

import static Utility.DataLoader.LoadFromPlaneText.loadFromResource;

public class Preprocessing {

    public static void basicPreprocessing(Corpus corpus) {
        removeNonLetterChars(corpus);
        toLowerCase(corpus);
        tokenize(corpus);

        List<String> basicStopWords = loadFromResource("stopwords");
        removeStopWords(corpus, basicStopWords);
    }

    public static void removeNonLetterChars(Corpus corpus) {
        corpus.forEach(f -> f.setRawText(f.getRawText()
                .replaceAll("[^A-Za-z]+", " ")
                .trim()));
    }

    public static void toLowerCase(Corpus corpus) {
        // think about change raw text to tokens
        corpus.forEach(f -> f.setRawText(f.getRawText().toLowerCase()));
    }

    public static void tokenize(Corpus corpus) {
        corpus.forEach(f -> f.setTokens(f.getRawText().split("\\s+")));
    }

    public static void removeStopWords(Corpus corpus, List<String> stopWords) {
        corpus.forEach(f -> f.getTokens().removeAll(stopWords));
    }

    public static void stemWords(Corpus corpus) {
        PorterStemmer porterStemmer = new PorterStemmer();
        corpus.forEach(f -> f.getTokens().replaceAll(porterStemmer::stemWord));
    }
}
