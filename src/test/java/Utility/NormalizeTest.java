package Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class NormalizeTest {

  @Test
  public void largestElementsInMapTest() {

    HashMap<String, Double> map1 = new HashMap<String, Double>() {{
      put("a", 3.0);
      put("b", 2.0);
      put("c", 1.0);
    }};

    HashMap<String, Double> map2 = new HashMap<String, Double>() {{
      put("a", 1.0);
      put("b", 3.0);
      put("c", 2.0);
    }};

    HashMap<String, Double> map3 = new HashMap<String, Double>() {{
      put("a", 4.0);
      put("b", 2.0);
      put("c", 2.0);
    }};

    List<Map<String, Double>> mapList = new ArrayList<>(Arrays.asList(map1, map2, map3));

    Map<String, Double> bgmp = Normalize.findBiggestInMap(mapList);

    Assert.assertEquals(bgmp.get("a"), 4.0, 0.001);
    Assert.assertEquals(bgmp.get("b"), 3.0, 0.001);
    Assert.assertEquals(bgmp.get("c"), 2.0, 0.001);
  }
}