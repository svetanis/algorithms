package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of positive numbers, find the total number
//of subsets whose sum is equal to a given number ‘S’.

// the fourth and last version of this problem: SubSetSumCountRecursive ->
// SubSetSumCountTopDown -> SubSetSumCountBottomUp -> here.
// same collapse as SubSetSumSpaceOptimized, with + in place of ||.
// counting is where a wrong loop order hurts most: it returns a
// plausible number rather than an obviously wrong answer.

public final class SubSetSumCountSpaceOptimized {

  public static int count(int[] a, int sum) {
    // Time complexity: O(n * sum)
    // Space complexity: O(sum)

    int[] dp = new int[sum + 1];

    // sum 0 is reachable exactly one way: take nothing
    dp[0] = 1;

    for (int v : a) {
      // three loop orders, three different questions, same arithmetic:
      // item outer + descending -> subsets, each value used once (this file)
      // item outer + ascending  -> unbounded combinations (LC 518)
      // sum outer  + item inner -> ordered sequences (LC 377)
      // for a = {1, 2, 3} and sum = 4 they return 1, 4 and 7.
      for (int s = sum; s >= v; --s) {
        dp[s] += dp[s - v];
      }
    }
    return dp[sum];
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(count(a1, 9));

    int[] a2 = { 1, 1, 2, 3 };
    System.out.println(count(a2, 4));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(count(a3, 9));

    // {} and {0} both sum to 0
    int[] a4 = { 2, 0, 1 };
    System.out.println(count(a4, 0)); // 2
  }
}
