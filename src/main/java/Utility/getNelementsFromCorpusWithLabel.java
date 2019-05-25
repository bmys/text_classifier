package Utility;

import Model.Corpus;
import Model.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class getNelementsFromCorpusWithLabel {

  public static Corpus getNElements(int n, Corpus corpus, String label, List<String> labels) {
    List<Document> documents = new ArrayList<>();
    for (String l : labels) {
      documents.addAll(
          corpus.getDocuments().stream().filter(o -> o.getLabels().get(label).get(0).equals(l))
              .limit(n).collect(
              Collectors.toList()));
    }

    return new Corpus(documents, "knn_docs");
  }
}
