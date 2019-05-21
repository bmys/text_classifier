package Features.basicFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

public class KeywordsQuantity implements FeatureExtractor<Double> {

  private List<String> keywords;

  public KeywordsQuantity(List<String> keywords) {
    this.keywords = keywords;
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    long keywordCount = document.getTokens().stream().filter(o -> keywords.contains(o)).count();

    String name = "KeywordsQuantity";
    return new SimpleEntry<>(name, (double) keywordCount);
  }
}
