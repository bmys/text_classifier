package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map.Entry;

public class UniqueToAllTokensRatio implements FeatureExtractor<Double> {

  private HashSet<String> keyWordSet;

  public UniqueToAllTokensRatio(HashSet<String> keyWordSet) {
    this.keyWordSet = keyWordSet;
  }

  @Override
  public Entry<String, Double> extract(Document document) {

    double uniqueWords = (double) (new HashSet<>(document.getTokens()).size());
    double allWords = (double) (document.getTokens().size());

    String name = "UniqueToAllTokensRatio";
    return new SimpleEntry<>(name, uniqueWords / allWords);
  }
}
