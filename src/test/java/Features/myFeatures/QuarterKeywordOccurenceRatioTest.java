package Features.myFeatures;

import static org.junit.Assert.assertEquals;

import Features.FeatureExtractor;
import Model.Document;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import org.junit.Before;
import org.junit.Test;

public class QuarterKeywordOccurenceRatioTest {

  FeatureExtractor<Double> feature;
  List<String> keywords = Arrays.asList("usa", "dollar");
  List<String> tokens = Arrays.asList("alfa", "usa", "have", "dollar");
  Document doc = new Document();

  @Before
  public void initialize() {
    feature = new QuarterKeywordOccurenceRatio(new HashSet<>(keywords));
    doc.setTokens(tokens);
  }

  @Test
  public void testQuarters() {
    Entry<String, Double> result = feature.extract(doc);
    assertEquals(1.0, result.getValue(), 0.1);
  }

  @Test
  public void twoKeywordInMiddle() {
    doc.setTokens(Arrays.asList("alfa", "usa", "usa", "dollar"));

    Entry<String, Double> result = feature.extract(doc);
    assertEquals(0.5, result.getValue(), 0.1);
  }
}