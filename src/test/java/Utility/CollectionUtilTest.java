package Utility;

import static Utility.CollectionUtil.mostCommonElement;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class CollectionUtilTest {

  @Test
  public void splitListByPercent() {
  }

  @Test
  public void getLabelsWithFrequency() {
  }

  @Test
  public void mostCommonElementTest() {
    List<String> list = Arrays.asList("hello", "hello", "world", "!");

    assertEquals("hello", mostCommonElement(list));

    list = Arrays.asList("hello", "world", "world", "!");

    assertEquals("world", mostCommonElement(list));

    // to fix in future
    list = Arrays.asList("hello", "hello", "world", "world");
    assertEquals("world", mostCommonElement(list));
  }

  @Test
  public void getMostCommonLabel() {
  }
}