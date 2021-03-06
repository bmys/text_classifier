package Features.basicFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class LengthFeature implements FeatureExtractor<Double> {

  @Override
  public Entry<String, Double> extract(Document document) {

    String name = "Length";
    return new SimpleEntry<>(name, (double) document.getTokens().size());
  }
}
