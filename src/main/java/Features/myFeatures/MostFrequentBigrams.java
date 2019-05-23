package Features.myFeatures;

import static Utility.CollectionUtil.pairComparatorAsc;

import Features.FeatureExtractor;
import Model.Document;
import com.google.common.collect.MinMaxPriorityQueue;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;

public class MostFrequentBigrams implements FeatureExtractor<String> {

  int count;

  public MostFrequentBigrams(int count) {
    this.count = count;
  }

  @Override
  public Entry<String, String> extract(Document document) {
    String result = StringUtils.join(document.getTokens(), "");

    List<String> bigrams = new LinkedList<>();

    for (int i = 0; i < result.length() - 1; i++) {
      bigrams.add(result.substring(i, i + 2));
    }

    MinMaxPriorityQueue<Pair<Double, String>> mostCommonBigrams =
        MinMaxPriorityQueue.orderedBy(pairComparatorAsc)
            .maximumSize(count)
            .create();

    for (String bigram : new HashSet<>(bigrams)) {
      double count = (double) Collections.frequency(bigrams, bigram);
      mostCommonBigrams.add(new Pair<>(count, bigram));
    }

    String bigramscat = StringUtils.join(mostCommonBigrams.stream().map(Pair::getValue).collect(
        Collectors.toList()), "");

    String name = "MostFrequentBigrams";
    return new SimpleEntry<>(name, bigramscat);
  }
}
