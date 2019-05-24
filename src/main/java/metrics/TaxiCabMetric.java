package metrics;

import static Utility.StringDistance.levenshteinDistance;

import Model.Document;
import java.util.Map;

public class TaxiCabMetric implements Metric {

  @Override
  public double getDistance(Document doc1, Document doc2) {
    double distance = 0;

    Map<String, Double> doc1num = doc1.getNumericFeatures();
    Map<String, Double> doc2num = doc2.getNumericFeatures();

    if (doc1num.size() != 0 && doc2num.size() != 0) {
      for (String featureName : doc1.getNumericFeatures().keySet()) {
        double diff = Math.abs(doc1num.get(featureName) - doc2num.get(featureName));
        distance += diff;
      }
    }

    Map<String, String> doc1str = doc1.getStringFeatures();
    Map<String, String> doc2str = doc2.getStringFeatures();

    if (doc1str.size() != 0 && doc2str.size() != 0) {
      for (String featureName : doc1.getNumericFeatures().keySet()) {
        distance += levenshteinDistance(doc1str.get(featureName), doc2str.get(featureName));
      }
    }

    return distance;
  }
}
