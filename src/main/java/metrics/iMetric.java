package metrics;

import java.util.List;

public interface iMetric<T> {
    double getDistance(List<T> a, List<T> b);
}
