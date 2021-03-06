package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import com.google.common.collect.Lists;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

public class QuarterKeywordOccurenceRatio implements FeatureExtractor<Double> {

  private HashSet<String> keyWordSet;
  String name = "QuarterKeywordOccurenceRatio";
  public QuarterKeywordOccurenceRatio(HashSet<String> keyWordSet) {
    this.keyWordSet = keyWordSet;
  }

  @Override
  public Entry<String, Double> extract(Document document) {
    List<String> arr = document.getTokens();
    List<List<String>> quarters;

    if (arr.size() < 4) {
      return new SimpleEntry<>(name, 0d);
    }

    try {
      quarters = Lists.partition(arr, arr.size() / 4);
    } catch (IllegalArgumentException e) {
      int l = 5;
      quarters = Arrays.asList(arr);
    }


    double firstQuarter = quarters.get(0).stream().filter(o -> keyWordSet.contains(o)).count();
    double secondQuarter = quarters.get(1).stream().filter(o -> keyWordSet.contains(o)).count();
    double thirdQuarter = quarters.get(2).stream().filter(o -> keyWordSet.contains(o)).count();
    double fourthQuarter = quarters.get(3).stream().filter(o -> keyWordSet.contains(o)).count();

    double counter = firstQuarter + fourthQuarter;
    double denominator = secondQuarter + thirdQuarter;

    if (denominator == 0 || counter == 0) {
      return new SimpleEntry<>(name, 0.0);
    }

    return new SimpleEntry<>(name, counter / denominator);
  }
}
