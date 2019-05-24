package Utility;

import com.google.common.collect.Iterables;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Normalize {

  public static Map<String, Double> findBiggestInMap(List<Map<String, Double>> vectors) {
    Map<String, Double> biggest = new HashMap<>(vectors.get(0));

    for (String feature : vectors.get(0).keySet()) {
      for (Map<String, Double> vector : Iterables.skip(vectors, 1)) {
        if (biggest.get(feature) < vector.get(feature)) {
          biggest.put(feature, vector.get(feature));
        }
      }
    }

    return biggest;
  }
}
