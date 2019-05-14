package Features;

import Model.Document;

public interface FeatureExtractor {
    Feature extract(Document document);
}
