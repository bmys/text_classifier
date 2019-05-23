package Features.myFeatures;

import Features.FeatureExtractor;
import Model.Document;
import Utility.FixedTreeMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;

public class MostFrequentBigrams implements FeatureExtractor<String> {

  @Override
  public Entry<String, String> extract(Document document) {
    String result = StringUtils.join(document.getTokens(), "");

    List<String> bigrams = new LinkedList<>();

    for (int i = 0; i < result.length() - 2; i++) {
      bigrams.add(result.substring(i, i + 1));
    }

    FixedTreeMap<String> mostCommonBigrams = new FixedTreeMap<>(5, true);

    for (String bigram : new HashSet<>(bigrams)) {
      double count = (double) Collections.frequency(bigrams, bigram);
      mostCommonBigrams.put(count, bigram);
    }

    // check if sorted?
    String bigramscat = StringUtils.join(mostCommonBigrams.values(), "");

    String name = "MostFrequentBigrams";
    return new SimpleEntry<>(name, bigramscat);
  }
}
