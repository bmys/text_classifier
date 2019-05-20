package Features;

import Model.Document;
import java.util.Map.Entry;

public interface FeatureExtractor<T> {

  Entry<String, T> extract(Document document);
}
