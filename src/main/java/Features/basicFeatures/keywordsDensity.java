package Features.basicFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

public class keywordsDensity implements FeatureExtractor<Double> {

  private List<String> keywords;

  public keywordsDensity(List<String> keywords) {
    this.keywords = keywords;
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    // ilość słów kluczowych w dokumencie
    long keywordCount = document.getTokens().stream().filter(o -> keywords.contains(o)).count();

    // gęstość, ilość słów kluczowych do ilości wszystkich słów
    double denisity = keywordCount / document.getTokens().size();

    String name = "keywordsDensity";
    return new SimpleEntry<>(name, denisity);
  }
}
