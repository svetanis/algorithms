package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative numbers, find the total number
//of subsets whose sum is equal to a given number ‘S’.

// same table as SubSetSumBottomUp -- dp[item][capacity], the axes of
// dp/knapsack/Knapsack01BottomUp. the boolean || becomes +, so the table
// counts subsets instead of reporting whether one exists.

public final class SubSetSumCountBottomUp {

  public static int count(int[] a, int sum) {
    // Time complexity: O(n * sum)

    int n = a.length;

    // the value of dp[i][s] is the number of subsets
    // of set[0 ... i - 1] with sum equal to s
    int[][] dp = new int[n + 1][sum + 1];

    // only the empty set is seeded: it reaches sum 0 one way, and
    // nothing else. the rest of row 0 is already 0.
    // seeding dp[i][0] = 1 for every i would be wrong -- with a zero
    // in the set there are 2 ways to reach sum 0, not 1.
    dp[0][0] = 1;

    // fill the subset table in bottom up manner.
    // s starts at 0, not 1: column 0 is a real subproblem that a
    // zero-valued element can double, so it has to be recomputed too
    for (int i = 1; i <= n; ++i) {
      for (int s = 0; s <= sum; ++s) {
        dp[i][s] = dp[i - 1][s];

        if (s >= a[i - 1]) {
          int incl = dp[i - 1][s - a[i - 1]];
          int excl = dp[i - 1][s];
          dp[i][s] = incl + excl;
        }
      }
    }
    return dp[n][sum];
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
