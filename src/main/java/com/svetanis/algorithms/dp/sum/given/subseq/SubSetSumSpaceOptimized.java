package com.svetanis.algorithms.dp.sum.given.subseq;

//Given a set of non-negative integers, and a value sum,
//determine if there is a subset of the given set with sum equal to given sum.

// the fourth and last version of this problem:
// SubSetSumRecursive -> SubSetSumTopDown -> SubSetSumBottomUp -> here.
// SubSetSumBottomUp reads only row i - 1, never row i, so one row is enough.

public final class SubSetSumSpaceOptimized {

  public static boolean isSum(int[] a, int sum) {
    // Time complexity: O(n * sum)
    // Space complexity: O(sum)

    boolean[] dp = new boolean[sum + 1];

    // sum 0 is always reachable: take nothing
    dp[0] = true;

    for (int v : a) {
      // descending is required, not a preference.
      // with one row there is nothing left to say which row a cell holds,
      // and the loop direction is what carries that.
      // going down, dp[s - v] has not been touched yet in this pass,
      // so it still holds row i - 1 and v is used at most once.
      // going up, dp[s - v] would already carry v, which is
      // unbounded knapsack -- a different problem, and it fails silently.
      for (int s = sum; s >= v; --s) {
        dp[s] = dp[s] || dp[s - v];
      }
    }
    return dp[sum];
  }

  public static void main(String[] args) {
    int[] a1 = { 3, 34, 4, 12, 5, 2 };
    System.out.println(isSum(a1, 9));

    int[] a2 = { 1, 2, 3, 7 };
    System.out.println(isSum(a2, 6));

    int[] a3 = { 1, 2, 7, 1, 5 };
    System.out.println(isSum(a3, 10));

    int[] a4 = { 1, 3, 4, 8 };
    System.out.println(isSum(a4, 6));
  }
}
