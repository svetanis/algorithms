package com.svetanis.algorithms.dp.common;

public final class SubSeqPatternMatchingRecursive {

  public static int count(String str, String pat) {
    int n = str.length();
    int m = pat.length();
    return count(str, pat, n, m);
  }

  private static int count(String str, String pat, int n, int m) {
    // Time Complexity: O(2^n)

    if ((n == 0 && m == 0) || m == 0) {
      return 1;
    }

    if (n == 0) {
      return 0;
    }

    int incl = 0;
    if (str.charAt(n - 1) == pat.charAt(m - 1)) {
      incl = count(str, pat, n - 1, m - 1);
    }
    int excl = count(str, pat, n - 1, m);
    return incl + excl;
  }

  public static void main(String[] args) {
    System.out.println(count("baxmx", "ax"));
    System.out.println(count("tomorrow", "tor"));
  }
}
