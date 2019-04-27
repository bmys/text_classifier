package FeatureExtractors;

import java.util.*;
import model.Entry;

public class RepeatedWordFeature implements iFeatureExtractor{

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        Set<String> words = new HashSet<>(text);
        int occurrences = 0;

        for(String word: words){
            if(Collections.frequency(text, word) > 1){
                occurrences++;
            }
        }
        return new Entry<>("RepeatedWordFeature", Math.log(1 + occurrences * 1.0d));
    }
}
