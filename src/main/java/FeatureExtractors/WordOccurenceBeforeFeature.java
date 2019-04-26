package FeatureExtractors;

import model.Entry;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordOccurenceBeforeFeature implements iFeatureExtractor {
    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        Set<String> previousWords = new HashSet<>();
        double counter = 0.0d;
        double div = 1;

        for(String word: text){
            if(previousWords.contains(word)){
                counter += 1.0 / div;
            }
            else{
                previousWords.add(word);
            }
            div += 0.1;
        }
        return new Entry<>("WordOccurenceBeforeFeature", counter);
    }
}
