package metrics;

import Model.Document;

public interface Metric {

  double getDistance(Document doc1, Document doc2);
}
