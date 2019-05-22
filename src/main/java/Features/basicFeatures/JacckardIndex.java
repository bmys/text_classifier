package Features.basicFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class JacckardIndex implements FeatureExtractor<Double> {

  HashSet<String> keyWordSet;

  public JacckardIndex(List<String> keywords) {
    this.keyWordSet = new HashSet<>(keyWordSet);
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    HashSet<String> documentTokens = new HashSet<>(document.getTokens());
    HashSet<String> intersection = new HashSet<>(keyWordSet);

    intersection.retainAll(documentTokens);

    Double counter = (double) intersection.size();

    HashSet<String> sumSet = new HashSet<>(keyWordSet);
    sumSet.addAll(documentTokens);

    Double denominator = (double) sumSet.size();

    Double jacckardIndex = counter / denominator;

    String name = "JacckardIndex";
    return new SimpleEntry<>(name, jacckardIndex);
  }
}
