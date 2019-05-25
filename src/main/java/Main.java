import static Utility.CollectionUtil.splitListByPercent;
import static Utility.ExtractKeywords.extractKeywordsIDF;
import static Utility.ExtractKeywords.inverseDocumentFrequency;
import static Utility.getNelementsFromCorpusWithLabel.getNElements;
import static Utility.toLatex.mapToLatex;
import static Utility.toLatex.mapToPercentLatex;

import Classifier.KNN;
import Classifier.Predictor;
import Features.myFeatures.AvgKeywordPositionFromMiddle;
import Features.myFeatures.FirstSentenceFeature;
import Features.myFeatures.KeywordsWeightedSum;
import Features.myFeatures.MostFrequentBigrams;
import Features.myFeatures.QuarterKeywordOccurenceRatio;
import Features.myFeatures.UniqueToAllTokensRatio;
import Model.Corpus;
import Model.Document;
import Utility.DataLoader.LoadSGML;
import Utility.Preprocessing;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.util.Pair;
import metrics.EuclideanMetric;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello World!");

    List<String> locations = Arrays
        .asList("japan", "west-germany", "canada", "usa", "france", "uk");

    try {
      // Data loading
      List<Document> documents = LoadSGML
          .loadFromDir("/home/arch/IdeaProjects/text_classifier_new/src/main/resources/reuters",
              locations);

      Pair<List<Document>, List<Document>> twoSets = splitListByPercent(documents, 60);

      // Split to sets
      Corpus corpus = new Corpus(twoSets.getKey(), "train");
      Corpus testCorpus = new Corpus(twoSets.getValue(), "test");

      // Clean up data
      Preprocessing.basicPreprocessing(corpus);
      Preprocessing.basicPreprocessing(testCorpus);
      System.out.println("Preprocessing skończony");

      // Keywords generation
      List<String> keywords = new ArrayList<>();

      // IDF for all labels
      Map<String, Double> idf = inverseDocumentFrequency(
          corpus.stream().map(Document::getTokens).collect(
              Collectors.toList()));

      for (String location : locations) {
        List<List<String>> k = corpus
            .stream()
            .filter(o -> o.getLabels().get("locations").get(0).equals(location))
            .map(Document::getTokens)
            .collect(Collectors.toList());

        List<String> keywordsIDF = extractKeywordsIDF(k, idf, 15);
        keywords.addAll(keywordsIDF);
      }
      keywords.remove("");
      keywords = new ArrayList<String>(new HashSet<>(keywords));
      System.out.println("Słowa kluczowe: ");
      System.out.println(keywords);

//      List<String> keywords = extractKeywords(k, 15);


      // Features
      AvgKeywordPositionFromMiddle avg = new AvgKeywordPositionFromMiddle(keywords);
      FirstSentenceFeature firstSentenceFeature = new FirstSentenceFeature(1);
      KeywordsWeightedSum keywordsWeightedSum = new KeywordsWeightedSum(new HashSet<>(keywords));
      MostFrequentBigrams bigrams = new MostFrequentBigrams(5);
      QuarterKeywordOccurenceRatio quarterKeywordOccurenceRatio = new QuarterKeywordOccurenceRatio(
          new HashSet<>(keywords));
      UniqueToAllTokensRatio uniqueToAllTokensRatio = new UniqueToAllTokensRatio();


      System.out.println("Ustalanie cechy skończone");

      // Pobieranie elementów do zimnego startu
      Corpus knnCorpus = getNElements(100,
          corpus,
          "locations",
          Arrays.asList("japan", "west-germany", "canada", "usa", "france", "uk"));

      KNN knn = new KNN(knnCorpus, new EuclideanMetric(), 5, "locations");
      Predictor predictor = new Predictor(knn, "locations");

      // Dodawanie cech
//      corpus.forEach(o -> o.setStringFeature(firstSentenceFeature.extract(o)));
//      testCorpus.forEach(o -> o.setStringFeature(firstSentenceFeature.extract(o)));
//
//      corpus.forEach(o -> o.setStringFeature(bigrams.extract(o)));
//      testCorpus.forEach(o -> o.setStringFeature(bigrams.extract(o)));
//
//      corpus.forEach(o -> o.setNumericFeature(avg.extract(o)));
//      testCorpus.forEach(o -> o.setNumericFeature(avg.extract(o)));
//
//      corpus.forEach(o -> o.setNumericFeature(keywordsWeightedSum.extract(o)));
//      testCorpus.forEach(o -> o.setNumericFeature(keywordsWeightedSum.extract(o)));
//
//      corpus.forEach(o -> o.setNumericFeature(quarterKeywordOccurenceRatio.extract(o)));
//      testCorpus.forEach(o -> o.setNumericFeature(quarterKeywordOccurenceRatio.extract(o)));

      corpus.forEach(o -> o.setNumericFeature(uniqueToAllTokensRatio.extract(o)));
      testCorpus.forEach(o -> o.setNumericFeature(uniqueToAllTokensRatio.extract(o)));

      System.out.println("cecha testowy skończone");

      System.out.println("Zaczynam klasyfikacje");
      System.out.println("Rozmiar zbioru testowego: " + testCorpus.size());

      int i = 1;
      for (Document doc : testCorpus) {
        System.out.println(i + " / " + testCorpus.size());
        predictor.predict(doc);
        i++;
      }

//      testCorpus.forEach(predictor::predict);

      System.out.println(predictor.getResults());

      System.out.println(mapToLatex(predictor.getResults(), "bla"));
      System.out.println(mapToPercentLatex(predictor.getResults(), "bla"));


    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
