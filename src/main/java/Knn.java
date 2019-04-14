import metrics.iMetric;
import model.Corpus;
import org.jsoup.nodes.Document;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knn {
    private int k;
    private iMetric metric;
    private model.Corpus corpus;

    public Knn(int k, iMetric metric, Corpus corpus) {
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
        List<String> mostCommon = new LinkedList<>();

        for(int i=0; i<k; i++){
            String word = predict.get(i).getKey().getLabels().get("locations").get(0);
            mostCommon.add(word);
//            System.out.println(mostCommon);
        }
        String max = mostCommon.get(0);
        int maxi = Collections.frequency(mostCommon, max);

        for (String key : mostCommon) {
            int curr = Collections.frequency(mostCommon, key);

            if(maxi < curr){
                maxi = curr;
                max = key;
            }
        }
//        System.out.println(max);
//        System.out.print("max: ");
//        Stream.of(mostCommon)
//                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
//                .entrySet()
//                .stream()
//                .max(Comparator.comparing(Map.Entry::getValue)).get(0);


        return max;
    }
}
