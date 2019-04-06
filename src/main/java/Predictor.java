import metrics.EuclideanMetric;
import model.Corpus;

import java.util.List;

public class Predictor {
    private Corpus corpus;
    private List<iFeatureExtractor> featureExtractorList;

    public Predictor(Corpus corpus, List<iFeatureExtractor> featureExtractorList) {
        this.corpus = corpus;
        this.featureExtractorList = featureExtractorList;

        for(model.Document doc: corpus.getDocuments()){
            for(iFeatureExtractor fex: this.featureExtractorList){
                doc.setFeature(fex.getFeatureValue(doc.getTokens()));
            }
        }

    }

    public String predict(model.Document doc, int n){
        Knn knn = new Knn(n, new EuclideanMetric(), this.corpus);
        knn.predict(doc);
        return "xDD";
    }
}
