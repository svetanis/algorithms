package com.svetanis.algorithms.dp.editdist;

import static java.lang.Math.min;

public final class EditDistanceDynamic {

  public static int editDist(String s1, String s2) {
    // Time complexity: O(n * m)

    int n = s1.length();
    int m = s2.length();

    int[][] table = new int[n + 1][m + 1];

    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        if (i == 0) {
          table[i][j] = j;
        } else if (j == 0) {
          table[i][j] = i;
        } else {
          int delete = table[i - 1][j] + 1;
          int insert = table[i][j - 1] + 1;
          int diff = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
          int replace = diff + table[i - 1][j - 1];
          table[i][j] = min(min(delete, insert), replace);
        }
      }
    }
    return table[n][m];
  }

  public static void main(String[] args) {
    String str1 = "Zeil", str2 = "trials";
    System.out.println(editDist(str1, str2));

    String str3 = "cat", str4 = "act";
    System.out.println(editDist(str3, str4));

    String str5 = "COMBO", str6 = "COIN";
    System.out.println(editDist(str5, str6));
    
    String str7 = "Anshuman", str8 = "Antihuman";
    System.out.println(editDist(str7, str8));    
  }
}
