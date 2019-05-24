package Utility;

import static Utility.CollectionUtil.pairComparatorAscValue;

import com.google.common.collect.MinMaxPriorityQueue;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ExtractKeywords {

  public static List<String> extractKeywords(List<List<String>> corpus, int count) {
    // todo: test
    Map<String, Double> idf = inverseDocumentFrequency(corpus);
    Map<String, Double> keywords = new HashMap<>();

    for (List<String> document : corpus) {
      Map<String, Double> tf = termFrequency(document);

      for (String token : tf.keySet()) {
        double tfidf = tf.get(token) * idf.get(token);
        keywords.merge(token, tfidf, Double::sum);
      }
    }
// check this
//    Map<String, Double> s = keywords.entrySet().stream().filter(o -> o.getValue() > 0.4).collect(Collectors.toMap(
//        Entry::getKey, Entry::getValue));

    MinMaxPriorityQueue<Map.Entry<String, Double>> bestKeywords =
        MinMaxPriorityQueue.orderedBy(pairComparatorAscValue)
            .maximumSize(count)
            .create();

    bestKeywords.addAll(keywords.entrySet());

    return bestKeywords.stream().map(Entry::getKey).collect(Collectors.toList());
  }

  public static Map<String, Double> termFrequency(List<String> document) {
    Map<String, Double> tf = new HashMap<>();

    double totalTermCount = (double) document.size();

    for (String token : new HashSet<>(document)) {
      tf.put(token, (double) Collections.frequency(document, token) / totalTermCount);
    }

    return tf;
  }

  public static Map<String, Double> inverseDocumentFrequency(List<List<String>> corpus) {

    double documentsCount = (double) corpus.size();
    Map<String, Integer> freq = new HashMap<>();

    for (List<String> document : corpus) {
      for (String token : new HashSet<>(document)) {
        freq.merge(token, 1, Integer::sum);
      }
    }

    return freq.entrySet().stream()
        .collect(
            Collectors
                .toMap(Entry::getKey, o -> Math.log10(documentsCount / (double) o.getValue())));
  }
}
