package FeatureExtractors;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordOccurenceBeforeFeature implements iFeatureExtractor {
    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        Set<String> previousWords = new HashSet<>();
        double counter = 0.0d;

        for(String word: text){
            if(previousWords.contains(word)){
                counter += 1.0;
            }
            else{
                previousWords.add(word);
            }

        }


        return null;
    }
}
