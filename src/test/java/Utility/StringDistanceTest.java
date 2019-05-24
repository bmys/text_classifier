package Utility;

import static Utility.StringDistance.levenshteinDistance;

import org.junit.Assert;
import org.junit.Test;

public class StringDistanceTest {

  @Test
  public void levDist() {
    String str1 = "book";
    String str2 = "back";
    double val = levenshteinDistance(str1, str2);

    Assert.assertEquals(2.0, val, 0.1);
  }
}