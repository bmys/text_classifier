package Utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ExtractKeywords {

  public static List<String> extractKeywords(List<List<String>> corpus) {

    return null;
  }

  public Map<String, Double> termFequency(List<String> document) {
    Map<String, Double> tf = new HashMap<>();

    double totalTermCount = (double) document.size();

    for (String token : new HashSet<>(document)) {
      tf.put(token, (double) Collections.frequency(document, token) / totalTermCount);
    }

    return tf;
  }

  public Map<String, Double> inverseDocumentFrequency(List<List<String>> corpus) {

    double documentsCount = (double) corpus.size();
    Map<String, Integer> freq = new HashMap<>();

    for (List<String> document : corpus) {
      for (String token : new HashSet<>(document)) {
        freq.merge(token, 1, Integer::sum);
      }
    }

    return freq.entrySet().stream()
        .collect(
            Collectors.toMap(Entry::getKey, o -> Math.log(documentsCount / (double) o.getValue())));
  }
}
