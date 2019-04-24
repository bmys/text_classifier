import FeatureExtractors.iFeatureExtractor;
import metrics.BoostMetric;
import model.Corpus;

import java.util.List;

public class Predictor {
    private Corpus corpus;
    private List<iFeatureExtractor> featureExtractorList;

    Predictor(Corpus corpus, List<iFeatureExtractor> featureExtractorList) {
        this.corpus = corpus;
        this.featureExtractorList = featureExtractorList;

        for(model.Document doc: corpus.getDocuments()){
            for(iFeatureExtractor fex: this.featureExtractorList){
                doc.setFeature(fex.getFeatureValue(doc.getTokens()));
            }
        }
    }

    public String predict(model.Document doc, int n){

        for(iFeatureExtractor fex: this.featureExtractorList){
            doc.setFeature(fex.getFeatureValue(doc.getTokens()));
        }

        Knn knn = new Knn(n, new BoostMetric(), this.corpus);

        return knn.predict(doc);
    }
}
