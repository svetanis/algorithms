package com.svetanis.algorithms.slidingwindow.string;

import static com.svetanis.java.base.utils.Nums.isDivisible;

// given a string and a number k
// count substrings of length k 
// whose sum of ASCII value of 
// chars is divisible by k

public final class CountSubStrsASCIISumDivisibleByK {

  public static int count(String str, int k) {
    int sum = 0;
    int count = 0;

    for (int i = 0; i < k; i++) {
      sum += str.charAt(i);
    }

    if (isDivisible(sum, k)) {
      count++;
    }

    for (int i = k; i < str.length(); i++) {
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
