package Classifier;

import static Utility.CollectionUtil.getMostCommonLabel;

import Model.Corpus;
import Model.Document;
import Utility.FixedTreeMap;
import metrics.Metric;

public class KNN {

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
    FixedTreeMap<Document> fixedTreeMap = new FixedTreeMap<>(k, false);

    for (Document corpusDoc : corpus.getDocuments()) {
      double distance = metric.getDistance(doc, corpusDoc);
      fixedTreeMap.put(distance, corpusDoc);
    }

    return getMostCommonLabel(fixedTreeMap, predictedLabel);
  }
}
