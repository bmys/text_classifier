package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map.Entry;

public class KeywordsWeightedSum implements FeatureExtractor<Double> {

  private HashSet<String> keyWordSet;

  public KeywordsWeightedSum(HashSet<String> keyWordSet) {
    this.keyWordSet = keyWordSet;
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    double weightedSum = 0;
    double size = (double) document.getTokens().size();
    double i = size;

    for (String token : document.getTokens()) {
      if (keyWordSet.contains(token)) {
        weightedSum += (i / size);
      }
      i -= 1.0;
    }

    String name = "KeywordsWeightedSum";

    return new SimpleEntry<>(name, weightedSum);
  }
}
