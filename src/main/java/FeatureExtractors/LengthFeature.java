package FeatureExtractors;

import model.Document;
import model.Entry;

import java.util.List;
import java.util.Map;

public class LengthFeature implements iFeatureExtractor{

    private double avgLeng;

    public LengthFeature(model.Corpus corpus) {
        double leng = 0;
        for(Document doc: corpus.getDocuments()){
            leng += doc.getTokens().size();
        }
        this.avgLeng = leng / corpus.getDocumentsCount();

    }

    @Override
    public Map.Entry<String, Double> getFeatureValue(List<String> text) {
        return new Entry<>("leng", Math.log(text.size() / this.avgLeng));
    }
}
