package com.svetanis.algorithms.greedy;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;

import com.google.common.collect.ImmutableList;

public final class MinFibonacciTermsGivenSum {

  public static int minTerms(int n) {
    List<Integer> list = fibNums(n);
    int count = 0;
    int right = list.size() - 1;
    while (n > 0) {
      int fib = list.get(right);
      count += n / fib;
      n = n % fib;
      right--;
    }
    return count;
  }

  private static ImmutableList<Integer> fibNums(int n) {
    List<Integer> list = newArrayList();
    list.add(0);
    list.add(1);
    list.add(1);

    int i = 3;
    while (true) {
      int next = list.get(i - 1) + list.get(i - 2);
      if (next > n) {
        return newList(list);
      }
      list.add(next);
      i++;
    }
  }

  public static void main(String[] args) {
    System.out.println(minTerms(4));
  }
}
