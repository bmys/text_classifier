package FeatureExtractors;

import model.Entry;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AvgDistFromMidFeature implements iFeatureExtractor {
    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {

        double counter = 0.0d;
        double mid = text.size() / 2.0;

        for(String word: text){
           counter += Math.abs(text.indexOf(word) / mid);
        }
        return new Entry<>("AvgDistFromMidFeature", counter);
    }
}
