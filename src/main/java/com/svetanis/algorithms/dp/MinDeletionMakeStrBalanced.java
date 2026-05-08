package com.svetanis.algorithms.dp;

// 1653. Minimum Deletions to Make String Balanced

public final class MinDeletionMakeStrBalanced {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static int minDeletionsSpaceOptim(String s) {
    int bcount = 0;
    int deletions = 0;
    for (char c : s.toCharArray()) {
      if (c == 'b') {
        bcount += 1;
      } else {
        deletions = Math.min(deletions + 1, bcount);
      }
    }
    return deletions;
  }

  public static int minDeletions(String s) {
    int bcount = 0;
    int n = s.length();
    int[] dp = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      if (s.charAt(i - 1) == 'b') {
        bcount += 1;
        dp[i] = dp[i - 1];
      } else {
        dp[i] = Math.min(dp[i - 1] + 1, bcount);
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(minDeletions("aababbab")); // 2
    System.out.println(minDeletions("bbaaaaabb")); // 2
  }
}
