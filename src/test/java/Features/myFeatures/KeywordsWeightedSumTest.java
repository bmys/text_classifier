package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KeywordsWeightedSumTest {

  FeatureExtractor<Double> feature;
  List<String> keywords = Arrays.asList("usa", "dollar", "bmw", "yen");
  List<String> tokens = Arrays.asList("usa", "have", "dollar");
  Document doc = new Document();

  @Before
  public void initialize() {
    feature = new KeywordsWeightedSum(new HashSet<>(keywords));
    doc.setTokens(tokens);
  }

  @Test
  public void weightedSumTest() {

    Map.Entry<String, Double> result = feature.extract(doc);

    Assert.assertEquals(1.33, result.getValue(), 0.1);
  }

  @Test
  public void zeroKeyWordsTest() {
    List<String> newTokens = Arrays.asList("china", "has", "dragons");
    doc.setTokens(newTokens);

    Map.Entry<String, Double> result = feature.extract(doc);
    Assert.assertEquals(0, result.getValue(), 0.1);
  }
}