package com.svetanis.algorithms.slidingwindow.string;

import static com.svetanis.java.base.utils.Nums.isDivisible;

public final class CountSubStrsASCIISumDivisibleByK {

  public static int count(String str, int k) {
    int n = str.length();
    int sum = 0;
    int count = 0;

    for (int i = 0; i < k; i++) {
      sum += str.charAt(i);
    }

    if (isDivisible(sum, k)) {
      count++;
    }

    for (int i = k; i < n; i++) {
      sum -= str.charAt(i - k);
      sum += str.charAt(i);

      if (isDivisible(sum, k)) {
        count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    String s1 = "bcgabc";
    System.out.println(count(s1, 3));

    String s2 = "adkf";
    System.out.println(count(s2, 3));
  }
}
