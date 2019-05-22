package Features.basicFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class AvgKeywordPosition implements FeatureExtractor<Double> {

  private HashSet<String> keyWordSet;

  public AvgKeywordPosition(List<String> keywords) {
    this.keyWordSet = new HashSet<>(keyWordSet);
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    int position = 0;
    int keywordCount = 0;
    int i = 1;

    for (String token : document.getTokens()) {
      if (keyWordSet.contains(token)) {
        position += i;
        ++keywordCount;
      }
      ++i;
    }

    String name = "AvgKeywordPosition";
    double result = (double) position / (double) keywordCount;

    return new SimpleEntry<>(name, result);
  }
}
