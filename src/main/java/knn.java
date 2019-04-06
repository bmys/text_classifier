import metrics.iMetric;
import model.Corpus;
import org.jsoup.nodes.Document;

import java.lang.reflect.Array;
import java.util.*;

public class knn {
    private int k;
    private iMetric metric;
    private model.Corpus corpus;

    public knn(int k, iMetric metric, Corpus corpus) {
        this.k = k;
        this.metric = metric;
        this.corpus = corpus;
    }

    public String predict(model.Document doc){
        List<String> features = new ArrayList<>(doc.getFeatures().keySet());
        List<Double> a = new LinkedList<>();

        for(String feature: features){
            a.add(doc.getFeatures().get(feature));
        }

        Map<model.Document, Double> distanceToDocuments= new HashMap<>();

        for(model.Document dc: corpus.getDocuments()){
            List<Double> b = new LinkedList<>();
            for(String feature: features){
                b.add(dc.getFeatures().get(feature));
            }
            double distance = this.metric.getDistance(a, b);

            distanceToDocuments.put(dc, distance);
        }

        distanceToDocuments = Vectorizer.sortByValued(distanceToDocuments);

        List<Map.Entry<model.Document, Double>> predict = new LinkedList(distanceToDocuments.entrySet());

        for(int i=predict.size()-this.k; i<predict.size(); i++){
            System.out.println(predict.get(i).getKey().getLabels());
        }

        return "xD";
    }
}
