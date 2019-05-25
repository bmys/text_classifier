import static Utility.CollectionUtil.splitListByPercent;
import static Utility.ExtractKeywords.extractKeywords;

import Classifier.KNN;
import Classifier.Predictor;
import Features.myFeatures.AvgKeywordPositionFromMiddle;
import Features.myFeatures.MostFrequentBigrams;
import Model.Corpus;
import Model.Document;
import Utility.DataLoader.LoadSGML;
import Utility.Preprocessing;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;
import metrics.EuclideanMetric;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello World!");

    List<String> locations = Arrays.asList("japan", "west-germany");

    try {
      List<Document> documents = LoadSGML
          .loadFromDir("/home/arch/IdeaProjects/text_classifier_new/src/main/resources/reuters",
              locations);

      Pair<List<Document>, List<Document>> twoSets = splitListByPercent(documents, 40);

      Corpus corpus = new Corpus(twoSets.getKey(), "train");
      Corpus testCorpus = new Corpus(twoSets.getValue(), "test");

      Preprocessing.basicPreprocessing(corpus);
      List<List<String>> k = corpus.stream().map(Document::getTokens).collect(Collectors.toList());
      // TODO: genrate IDFs for whole corpus
      List<String> keywords = extractKeywords(k, 15);

      AvgKeywordPositionFromMiddle avg = new AvgKeywordPositionFromMiddle(keywords);

      MostFrequentBigrams bigrams = new MostFrequentBigrams(5);

      corpus.forEach(o -> o.setNumericFeature(avg.extract(o)));
//      corpus.forEach(o -> o.setStringFeature(bigrams.extract(o)));

      KNN knn = new KNN(corpus, new EuclideanMetric(), 3, "locations");
      Predictor predictor = new Predictor(knn, "locations");

//      boolean m = predictor.predict(testCorpus.getDocuments().get(0));
//      System.out.println(m);
//      m = predictor.predict(testCorpus.getDocuments().get(1));
//      System.out.println(m);
//      m = predictor.predict(testCorpus.getDocuments().get(2));
//      System.out.println(m);
//      m = predictor.predict(testCorpus.getDocuments().get(3));
//      System.out.println(m);
//      m = predictor.predict(testCorpus.getDocuments().get(4));
//      System.out.println(m);

      // TODO: !!! calculate feature for test example <facepalm.gif>
      testCorpus.forEach(predictor::predict);
      System.out.println(predictor.getResults());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
