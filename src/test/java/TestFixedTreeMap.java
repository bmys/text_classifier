

import static org.junit.Assert.assertEquals;

import Utility.FixedTreeMap;
import com.google.common.collect.Sets;
import org.junit.Test;

public class TestFixedTreeMap {
  //protected FixedTreeMap<Integer> fixedTreeMap;

  protected void setUp() {
//    this.fixedTreeMap = new FixedTreeMap<>(3);
  }

  @Test
  public void MaxLengthDescTest() {
    FixedTreeMap<Integer> fixedTreeMap = new FixedTreeMap<>(3, true);
    fixedTreeMap.put(2.0, 1);
    fixedTreeMap.put(3.0, 2);
    fixedTreeMap.put(5.0, 3);
    assertEquals(fixedTreeMap.size(), 3);
    fixedTreeMap.put(6.0, 4);
    fixedTreeMap.put(4.0, 5);
    assertEquals(fixedTreeMap.size(), 3);
  }


  @Test
  public void MaxOrderDescTest() {
    FixedTreeMap<Integer> fixedTreeMap = new FixedTreeMap<>(3, true);
    fixedTreeMap.put(1.0, 1);
    fixedTreeMap.put(2.0, 2);
    fixedTreeMap.put(3.0, 3);
    assertEquals(Sets.newHashSet(1.0, 2.0, 3.0), fixedTreeMap.keySet());

    fixedTreeMap.put(4.0, 4);
    assertEquals(Sets.newHashSet(4.0, 3.0, 2.0), fixedTreeMap.keySet());

    fixedTreeMap.put(5.0, 5);
    assertEquals(Sets.newHashSet(5.0, 4.0, 3.0), fixedTreeMap.keySet());
  }

  @Test
  public void MaxLengthAscTest() {
    FixedTreeMap<Integer> fixedTreeMap = new FixedTreeMap<>(3, false);
    fixedTreeMap.put(2.0, 1);
    fixedTreeMap.put(3.0, 2);
    fixedTreeMap.put(5.0, 3);
    assertEquals(fixedTreeMap.size(), 3);
    fixedTreeMap.put(6.0, 4);
    fixedTreeMap.put(4.0, 5);
    assertEquals(fixedTreeMap.size(), 3);
  }


  @Test
  public void MaxOrderAscTest() {
    FixedTreeMap<Integer> fixedTreeMap = new FixedTreeMap<>(3, false);
    fixedTreeMap.put(1.0, 1);
    fixedTreeMap.put(2.0, 2);
    fixedTreeMap.put(3.0, 3);
    assertEquals(Sets.newHashSet(1.0, 2.0, 3.0), fixedTreeMap.keySet());

    fixedTreeMap.put(4.0, 4);
    assertEquals(Sets.newHashSet(1.0, 2.0, 3.0), fixedTreeMap.keySet());

    fixedTreeMap.put(0.0, 5);
    assertEquals(Sets.newHashSet(1.0, 2.0, 0.0), fixedTreeMap.keySet());
  }
}
