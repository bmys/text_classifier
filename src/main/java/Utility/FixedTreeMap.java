package Utility;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class FixedTreeMap<T> extends TreeMap<Double, T> {

  private int size;
  private boolean descending;

  public FixedTreeMap(int size, boolean descending) {
    super();
    this.size = size;
    this.descending = descending;
  }

  @Override
  public T put(Double a, T t) {
    // jeśli nie osiągnieto maksymalnej pojemności
    if (super.size() < size) {
      return super.put(a, t);
    }
    // mapa zapełniona
    else {

      if (descending) {
        return desc(a, t);
      } else {
        return asc(a, t);
      }
    }
  }

  private T desc(Double a, T t) {
    // TODO: Chnage this to strategy pattern
    // czy element mniejszy od najmniejszego w mapie?
    if (super.firstKey() > a) {
      return t;
    }
    // nowy element większy od najmniejszego w mapie
    else {
      super.remove(super.firstKey());
      return super.put(a, t);
    }
  }

  private T asc(Double a, T t) {
    // czy element większy od najmniejszego w mapie?
    if (super.lastKey() < a) {
      return t;
    }
    // nowy element mniejszy od największego w mapie
    else {
      super.remove(super.lastKey());
      return super.put(a, t);
    }
  }

  @Override
  public Collection<T> values() {
    return super.values();
  }

  @Override
  public Set<Entry<Double, T>> entrySet() {
    return super.entrySet();
  }
}

