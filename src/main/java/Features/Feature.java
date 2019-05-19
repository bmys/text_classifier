package Features;

public class Feature<T> {

  private T value;
  private FeatureType featureType;

  public Feature(T value, FeatureType featureType) {
    this.value = value;
    this.featureType = featureType;
  }

  T getValue() {
    return value;
  }

  public FeatureType getFeatureType() {
    return featureType;
  }

  enum FeatureType {
    STRING,
    NUMERIC
  }
}
