package FeatureExtractors;

import model.Entry;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SumOfKeywordGapsFeature implements iFeatureExtractor {
    private Set keywords;
    public SumOfKeywordGapsFeature( Set keywords) {
        this.keywords = keywords;
    }

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {

        int gapSum = 0;
        int counter = 0;
        double div = 1;

        for(String word: text){
            if(keywords.contains(word)){
                gapSum += counter;
                counter = 0;
            }
            else{
                counter += 1;
            }
        }
        gapSum += counter;
        return new Entry<>("SumOfKeywordGapsFeature", (double)gapSum);
    }
}
