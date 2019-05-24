package Classifier;

public interface Classifier<T, K> {

  T classify(K entity);

  void add(K entity);
}
