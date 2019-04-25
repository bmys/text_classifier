package FeatureExtractors;

import model.Entry;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KeyWordCounterFeature implements iFeatureExtractor {
    private List<String> keywords;
    private String label;

    public KeyWordCounterFeature(List<String> keywords, String label) {

        this.keywords = keywords;
        this.label = label;
    }

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        double counter =0;
        for(String keyword: keywords){
            counter += Collections.frequency(this.keywords, keyword);
        }
        return new Entry<>("keyword_counter_" + label, Math.log(counter));
    }
}
