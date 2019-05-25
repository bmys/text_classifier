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

    List<String> locations = Arrays.asList("japan", "west-germany", "canada");

    try {
      // Data loading
      List<Document> documents = LoadSGML
          .loadFromDir("/home/arch/IdeaProjects/text_classifier_new/src/main/resources/reuters",
              locations);

      Pair<List<Document>, List<Document>> twoSets = splitListByPercent(documents, 40);
      // Split to sets
      Corpus corpus = new Corpus(twoSets.getKey(), "train");
      Corpus testCorpus = new Corpus(twoSets.getValue(), "test");

      // Clean up data
      Preprocessing.basicPreprocessing(corpus);
      Preprocessing.basicPreprocessing(testCorpus);
      System.out.println("Preprocessing skończony");

      // Keywords generation
      List<List<String>> k = corpus.stream().map(Document::getTokens).collect(Collectors.toList());
      // TODO: genrate IDFs for whole corpus
      List<String> keywords = extractKeywords(k, 15);

      // Features
      AvgKeywordPositionFromMiddle avg = new AvgKeywordPositionFromMiddle(keywords);

      MostFrequentBigrams bigrams = new MostFrequentBigrams(5);

//      corpus.forEach(o -> o.setNumericFeature(avg.extract(o)));
      corpus.forEach(o -> o.setStringFeature(bigrams.extract(o)));
      System.out.println("Ustalanie cechy skończone");
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
//      testCorpus.forEach(o -> o.setNumericFeature(avg.extract(o)));

      testCorpus.forEach(o -> o.setStringFeature(bigrams.extract(o)));
      System.out.println("cecha testowy skończone");
      testCorpus.stream().limit(250).forEach(predictor::predict);
      System.out.println(predictor.getResults());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
