package FeatureExtractors;

import dataLoading.Vectorizer;
import model.Corpus;
import java.util.*;

public class FeatureExtractor {

    static Map binaryExtractor(List <String> text, List <String> model){
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for(String word: model){
            if(text.contains(word)){
                temp.put(word, 1);
            }
            else{
                temp.put(word, 0);
            }
        }
        return temp;
    }

    static Map countExtractor(List <String> text, List <String> model) {

        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for(String word : model) {
            int count = Collections.frequency(text, word);
            temp.put(word, count);
        }

        return temp;
    }

    static Map lengExtractor(List <String> text) {

        HashMap<String, Integer> temp = new LinkedHashMap<>();
        temp.put("leng", text.size());
        return temp;
    }

    static Map wordPositionExtractor(List <String> text, List <String> model) {

        HashMap<String, Integer> temp = new LinkedHashMap<>();

        for(String word : model) {
            if(text.contains(word)){
                temp.put(word, text.indexOf(word));
            }

        }
        return temp;
    }

    static Map TfIdf (List <String> text, List <String> model, List<List<String>> corpus){
        HashMap<String, Double> temp = new LinkedHashMap<>();

        int textLeng = text.size();

        for(String word : model) {
            if(text.contains(word)) {

                double tf = Collections.frequency(text, word) / textLeng;

                int documentsWithTerm = 0;

                for(List document: corpus){
                    if(document.contains(word)){
                        documentsWithTerm++;
                    }
                }

                double idf = Math.log(corpus.size() / documentsWithTerm);


                temp.put(word, tf * idf);
            }
        }

        return temp;
    }

    public static Map<String, Float> extractKeyWords(List<String> text, Corpus corpus ){
//        create set
        Set<String> wordsSet = new HashSet<>(text);
        Map<String,Float> keywords = new HashMap<>();
        for(String word: wordsSet){
            float tf = (float)Collections.frequency(text, word) / (float)text.size();
            float tfidf = tf * corpus.getWordIDF().get(word);
            keywords.put(word, tfidf);
        }

        return Vectorizer.sortByValuef(keywords);
    }
}


