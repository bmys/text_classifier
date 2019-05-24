package Classifier;

import Model.Corpus;
import Model.Document;
import org.apache.commons.collections4.map.MultiKeyMap;

/**
 * Klasa ta korzysta z klasyfikatora, liczy błąd oraz dodaje zkwalifikowany dokument do aktualnego
 * zbioru(zimny start)
 */
public class Predictor {

  private Classifier<String, Document> classifier;
  private Corpus dataSet;
  private MultiKeyMap<String, Integer> results = new MultiKeyMap<>();
  private String predictedLabel;

  public Predictor(Classifier<String, Document> classifier) {
    this.classifier = classifier;
  }

  public Predictor(Classifier<String, Document> classifier, Corpus dataSet, String predictedLabel) {
    this.classifier = classifier;
    this.dataSet = dataSet;
    this.predictedLabel = predictedLabel;
  }

  public Predictor(Classifier<String, Document> classifier, String predictedLabel) {
    this.classifier = classifier;
    this.predictedLabel = predictedLabel;
  }

  public boolean predict(Document doc) {
    String predicted = classifier.classify(doc);
    String actualValue = doc.getLabels().get(predictedLabel).get(0);
    int currentScore;
    // zwiększamy wartość w macierzy pomyłek
    try {
      currentScore = results.get(actualValue, predicted);
    } catch (NullPointerException e) {
      results.put(actualValue, predicted, 0);
      currentScore = 0;
    }

    results.put(actualValue, predicted, currentScore + 1);

    // zmieniamy lokacje na tę która została przewidziana
    doc.getLabels().get(predictedLabel).set(0, predicted);

    //    Document newDocument =
    // dodajemy do "modelu"
    // add to classifier instead
    //    dataSet.add(doc);
    classifier.add(doc);
    return predicted.equals(actualValue);
  }

  public Classifier<String, Document> getClassifier() {
    return classifier;
  }

  public void setClassifier(Classifier<String, Document> classifier) {
    this.classifier = classifier;
  }

  public Corpus getDataSet() {
    return dataSet;
  }

  public void setDataSet(Corpus dataSet) {
    this.dataSet = dataSet;
  }

  public MultiKeyMap<String, Integer> getResults() {
    return results;
  }

  public void setResults(MultiKeyMap<String, Integer> results) {
    this.results = results;
  }

  public String getPredictedLabel() {
    return predictedLabel;
  }

  public void setPredictedLabel(String predictedLabel) {
    this.predictedLabel = predictedLabel;
  }
}
