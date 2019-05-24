package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class AvgKeywordPositionFromMiddle implements FeatureExtractor<Double> {

  private HashSet<String> keyWordSet;

  public AvgKeywordPositionFromMiddle(List<String> keywords) {
    this.keyWordSet = new HashSet<>(keywords);
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    String name = "AvgKeywordPositionFromMiddle";

    // czy ma jakiekolwiek słowo kluczowe jak nie zwróć zero

    if (keyWordSet.stream().noneMatch(document.getTokens()::contains)) {
      return new SimpleEntry<>(name, 0.0);
    }

    int position = 0;
    int keywordCount = 0;
    int i = 1;
    double mid = (double) document.getTokens().size() / 2.0;

    for (String token : document.getTokens()) {
      if (keyWordSet.contains(token)) {
        position += Math.abs(i - mid);
        ++keywordCount;
      }
      ++i;
    }

    double result = (double) position / (double) keywordCount;

    return new SimpleEntry<>(name, result);
  }
}
