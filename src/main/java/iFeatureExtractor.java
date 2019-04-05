

import java.util.List;
import java.util.Map;

public interface iFeatureExtractor {
    Map.Entry<String, Double> getFeatureValue(List<String> text);
}
