package com.svetanis.algorithms.slidingwindow.array;

import static com.svetanis.java.base.collect.Lists.transform;
import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import java.util.List;

import com.svetanis.java.base.Pair;

// given a number in the form of a string
// find the max sum and product of m consecutive digits

public final class MaxSumAndProduct {

  public static Pair<Integer, Integer> maxSumProd(String str, int k) {
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int n = str.length();
    int sum = 0;
    int prod = 1;
    List<Character> chars = asList(toObject(str.toCharArray()));
    List<Integer> list = transform(chars, c -> parseInt(Character.toString(c)));

    for (int i = 0; i < k; i++) {
      sum += list.get(i);
      prod *= list.get(i);
    }

    int maxSum = sum;
    int maxProd = prod;
    for (int i = k; i < n; i++) {
      sum += list.get(i) - list.get(i - k);
      prod *= list.get(i) / list.get(i - k);
      maxSum = max(maxSum, sum);
      maxProd = max(maxProd, prod);
    }
    return Pair.build(maxSum, maxProd);
  }

  public static void main(String[] args) {

    String s1 = "3675356291";
    System.out.println(maxSumProd(s1, 5));

    String s2 = "2709360626";
    System.out.println(maxSumProd(s2, 5));

  }
}
