import static Utility.CollectionUtil.splitListByPercent;
import static Utility.ExtractKeywords.extractKeywords;

import Features.myFeatures.AvgKeywordPositionFromMiddle;
import Model.Corpus;
import Model.Document;
import Utility.DataLoader.LoadSGML;
import Utility.Preprocessing;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello World!");

    List<String> locations = Arrays.asList("japan");

    try {
      List<Document> documents = LoadSGML
          .loadFromDir("/home/arch/IdeaProjects/text_classifier_new/src/main/resources/reuters",
              locations);

      Pair<List<Document>, List<Document>> twoSets = splitListByPercent(documents, 40);

      Corpus corpus = new Corpus(twoSets.getKey(), "allData");
      Preprocessing.basicPreprocessing(corpus);
      List<List<String>> k = corpus.stream().map(Document::getTokens).collect(Collectors.toList());
      // TODO: genrate IDFs for whole corpus
      List<String> keywords = extractKeywords(k, 15);

      AvgKeywordPositionFromMiddle avg = new AvgKeywordPositionFromMiddle(keywords);
      corpus.forEach(o -> o.setNumericFeature(avg.extract(o)));

//      KNN knn = new KNN();
//      Predictor predictor = new Predictor();

      int w = 5;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
