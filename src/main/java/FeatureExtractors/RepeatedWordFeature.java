package FeatureExtractors;

import java.util.*;
import model.Entry;

public class RepeatedWordFeature implements iFeatureExtractor{

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        Set<String> words = new HashSet<>(text);
        int occurences = 0;

        for(String word: words){
            if(Collections.frequency(text, word) > 1){
                occurences++;
            }
        }
        return new Entry<String, Double>("RepeatedWordFeature", occurences * 1.0d);
    }
}
