package Classifier;

public interface Classifier<T, K> {

  T classify(K entity);
}
