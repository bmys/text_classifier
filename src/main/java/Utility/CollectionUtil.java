package Utility;

import Model.Document;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class CollectionUtil {

  public static <T> Pair<List<T>, List<T>> splitListByPercent(List<T> list, int percent) {
    int cutIdx = (list.size() * percent) / 100;
    List<T> firstList = list.subList(0, cutIdx);
    List<T> secondList = list.subList(cutIdx, list.size());
    return new Pair<>(firstList, secondList);
  }

  public static Map<String, Long> getLabelsWithFrequency(
      Map<Double, Document> documents, String label) {
    return documents.values().stream()
        .collect(
            Collectors.groupingBy(o -> o.getLabels().get(label).get(0), Collectors.counting()));
  }

  public static Comparator<Pair<Double, String>> pairComparatorDesc =
      Comparator.comparing(Pair::getKey);


  public static String getMostCommonLabel(Map<Double, Document> documents, String label) {
    Map.Entry<Double, Document> mostCommonLabel = documents.entrySet()
        .stream()
        .max(Map.Entry.comparingByKey())
        .orElse(null);
    if (mostCommonLabel == null) {
      return "";
    }
    return mostCommonLabel.getValue().getLabels().get(label).get(0);
  }

  // TODO: create this generic
  public static Comparator<Pair<Double, String>> pairComparatorAsc =
      (doubleTPair, t1) -> t1.getKey().compareTo(doubleTPair.getKey());

  public static Comparator<Pair<Double, Document>> documentPairComparatorAsc =
      (doubleTPair, t1) -> t1.getKey().compareTo(doubleTPair.getKey());

  // TODO: test it
  public static Comparator<Map.Entry<String, Double>> pairComparatorAscValue =
      (doubleTPair, t1) -> t1.getValue().compareTo(doubleTPair.getValue());

  public static Comparator<Map.Entry<String, Double>> pairComparatorDescValue =
      (doubleTPair, t1) -> doubleTPair.getValue().compareTo(t1.getValue());

  public static String mostCommonElement(List<String> list) {
    Optional<Entry<String, Long>> k = list.stream()
        .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
        .entrySet()
        .stream()
        .max(Comparator.comparing(Entry::getValue));

    if (k.isPresent()) {
      return k.get().getKey();
    } else {
      return null;
    }
  }
}
