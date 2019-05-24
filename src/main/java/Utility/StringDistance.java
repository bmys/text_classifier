package Utility;

import java.util.Arrays;

public class StringDistance {

  public static double hammingDistance(String doc1, String doc2) {
    int size = Math.max(doc1.length(), doc2.length());
    double result = (double) Math.abs(doc1.length() - doc2.length());
    for (int i = 0; i < size; i++) {
      if (doc1.charAt(i) != doc2.charAt(i)) {
        result += 1.0;
      }
    }
    return result;
  }

  public static double levenshteinDistance(String doc1, String doc2) {
    int[][] dp = new int[doc1.length() + 1][doc2.length() + 1];

    for (int i = 0; i <= doc1.length(); i++) {
      for (int j = 0; j <= doc2.length(); j++) {
        if (i == 0) {
          dp[i][j] = j;
        } else if (j == 0) {
          dp[i][j] = i;
        } else {
          dp[i][j] =
              min(
                  dp[i - 1][j - 1] + costOfSubstitution(doc1.charAt(i - 1), doc2.charAt(j - 1)),
                  dp[i - 1][j] + 1,
                  dp[i][j - 1] + 1);
        }
      }
    }

    return dp[doc1.length()][doc2.length()];
  }

  public static int costOfSubstitution(char a, char b) {
    return a == b ? 0 : 1;
  }

  public static int min(int... numbers) {
    return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
  }
}
