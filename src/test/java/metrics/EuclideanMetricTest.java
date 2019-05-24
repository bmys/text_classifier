package metrics;

import Model.Document;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EuclideanMetricTest {

  Document doc1 = new Document();
  Document doc2 = new Document();

  @Before
  public void initialize() {
    doc1.setNumericFeatures(
        new HashMap<String, Double>() {
          {
            put("a", 2.0);
            put("b", 1.0);
          }
        });

    doc2.setNumericFeatures(
        new HashMap<String, Double>() {
          {
            put("a", 1.0);
            put("b", 0.5);
          }
        });
  }

  @Test
  public void getDistance() {
    EuclideanMetric euclideanMetric = new EuclideanMetric();
    double distance = euclideanMetric.getDistance(doc1, doc2);
    Assert.assertEquals(1.118034, distance, 0.0001);

    doc1.setNumericFeatures(
        new HashMap<String, Double>() {
          {
            put("a", 2.0);
            put("b", 1.0);
            put("c", 8.0);
          }
        });

    doc2.setNumericFeatures(
        new HashMap<String, Double>() {
          {
            put("a", 1.0);
            put("b", 0.5);
            put("c", 4.0);
          }
        });

    distance = euclideanMetric.getDistance(doc1, doc2);
    Assert.assertEquals(4.153312, distance, 0.0001);
  }
}
