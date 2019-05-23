package Features.myFeatures;

import static org.junit.Assert.assertEquals;

import Model.Document;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class MostFrequentBigramsTest {

  @Test
  public void extract() {
    Document doc = new Document();
    List<String> tokens = Arrays.asList("ab", "ababxd", "xdxd");
    doc.setTokens(tokens);
    MostFrequentBigrams feature = new MostFrequentBigrams(3);
    Map.Entry<String, String> featureValue = feature.extract(doc);
    assertEquals("abxdba", featureValue.getValue());
  }
}