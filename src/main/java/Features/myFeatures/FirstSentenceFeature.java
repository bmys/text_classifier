package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class FirstSentenceFeature implements FeatureExtractor<String> {

  int kFirstWords;

  public FirstSentenceFeature(int kFirstWords) {
    this.kFirstWords = kFirstWords;
  }

  @Override
  public Entry<String, String> extract(Document document) {
    StringBuilder stringBuilder = new StringBuilder();
    int i = 0;
    for (String token : document.getTokens()) {
      if (i == kFirstWords) {
        break;
      }
      stringBuilder.append(token);
      ++i;
    }
    String name = "FirstSentence";
    return new SimpleEntry<>(name, stringBuilder.toString());
  }
}
