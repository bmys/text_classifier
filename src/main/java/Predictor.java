import FeatureExtractors.iFeatureExtractor;
import metrics.BoostMetric;
import metrics.iMetric;
import model.Corpus;

import java.util.List;

public class Predictor {
    private Corpus corpus;
    private List<iFeatureExtractor> featureExtractorList;
    private iMetric metric;

    Predictor(Corpus corpus, List<iFeatureExtractor> featureExtractorList, iMetric metric) {
        this.corpus = corpus;
        this.featureExtractorList = featureExtractorList;
        this.metric = metric;
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

        Knn knn = new Knn(n, metric, this.corpus);

        return knn.predict(doc);
    }
}
