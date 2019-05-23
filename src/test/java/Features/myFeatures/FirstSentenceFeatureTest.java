package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FirstSentenceFeatureTest {

  FeatureExtractor<String> feature;
  List<String> tokens = Arrays.asList("usa", "have", "dollar");
  Document doc = new Document();

  @Before
  public void initialize() {
    feature = new FirstSentenceFeature(1);
    doc.setTokens(tokens);
  }

  @Test
  public void firstWord() {
    Entry<String, String> k = feature.extract(doc);
    Assert.assertEquals("usa", k.getValue());
  }

  @Test
  public void twoFirstWord() {
    feature = new FirstSentenceFeature(2);
    Entry<String, String> k = feature.extract(doc);
    Assert.assertEquals("usahave", k.getValue());
  }

  @Test
  public void twoFirstWordInOneWordDoc() {

    doc.setTokens(Arrays.asList("usa"));
    feature = new FirstSentenceFeature(2);
    Entry<String, String> k = feature.extract(doc);
    Assert.assertEquals("usa", k.getValue());
  }
}