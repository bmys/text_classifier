package Classifier;

import static Utility.CollectionUtil.documentPairComparatorAsc;
import static Utility.CollectionUtil.mostCommonElement;

import Model.Corpus;
import Model.Document;
import com.google.common.collect.MinMaxPriorityQueue;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;
import metrics.Metric;

public class KNN implements Classifier<String, Document> {

  private Corpus corpus;
  private Metric metric;
  private int k;
  private String predictedLabel;

  public KNN(Corpus corpus, Metric metric, int k, String predictedLabel) {
    this.corpus = corpus;
    this.metric = metric;
    this.k = k;
    this.predictedLabel = predictedLabel;
  }

  public String classify(Document doc) {

    MinMaxPriorityQueue<Pair<Double, Document>> closestDocuments =
        MinMaxPriorityQueue.orderedBy(documentPairComparatorAsc).maximumSize(k).create();

    for (Document docInCorpus : corpus.getDocuments()) {
      double distance = metric.getDistance(doc, docInCorpus);
      closestDocuments.add(new Pair<>(distance, docInCorpus));
    }

    // Idea: change this to strategy pattern. (Conflict resolver)

    List<String> bestMatches =
        closestDocuments.stream()
            .map(o -> o.getValue().getLabels().get(predictedLabel).get(0))
            .collect(Collectors.toList());

    return mostCommonElement(bestMatches);
  }

  public void add(Document doc) {
    corpus.add(doc);
  }
}
