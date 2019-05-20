package Utility;

import Model.Document;
import java.util.List;
import java.util.Map;
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
}
