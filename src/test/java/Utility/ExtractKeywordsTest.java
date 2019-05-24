package Utility;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class ExtractKeywordsTest {

  List<String> doc1 = Arrays.asList("this", "a", "is", "a", "sample");
  List<String> doc2 = Arrays
      .asList("this", "is", "another", "another", "example", "example", "example");

  @Test
  public void extractKeywords() {

  }

  @Test
  public void termFrequency() {
    Map<String, Double> result = ExtractKeywords.termFrequency(doc1);
    Assert.assertEquals(0.2, result.get("this"), 0.1);

    result = ExtractKeywords.termFrequency(doc2);
    Assert.assertEquals(0.14, result.get("is"), 0.1);
    Assert.assertEquals(0.429, result.get("example"), 0.001);
  }

  @Test
  public void inverseDocumentFrequency() {
    Map<String, Double> result = ExtractKeywords
        .inverseDocumentFrequency(Arrays.asList(doc1, doc2));
    Assert.assertEquals(0.0, result.get("this"), 0.001);
    Assert.assertEquals(0.301, Math.log10(2.0), 0.001);
    Assert.assertEquals(0.301, result.get("example"), 0.001);
  }
}