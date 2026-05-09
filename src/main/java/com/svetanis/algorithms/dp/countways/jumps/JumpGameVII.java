package com.svetanis.algorithms.dp.countways.jumps;

// 1871. Jump Game VII

public final class JumpGameVII {
  // Time Complexity: O(n)
  // Space Complexity: O(n)

  public static boolean canReach(String s, int minJump, int maxJump) {
    int n = s.length();
    int[] prefix = new int[n + 1];
    prefix[1] = 1; // first position is reachable because we start at zero
    boolean[] dp = new boolean[n];
    dp[0] = true; // position 0 is a starting position
    for (int i = 1; i < n; i++) {
      if (s.charAt(i) == '0') {
        int left = Math.max(0, i - maxJump);
        int right = i - minJump;
        if (left <= right && prefix[right + 1] - prefix[left] > 0) {
          dp[i] = true;
        }
      }
      prefix[i + 1] = prefix[i] + ((dp[i] == true) ? 1 : 0);
    }
    return dp[n - 1];
  }

  public static void main(String[] args) {
    System.out.println(canReach("011010", 2, 3)); // true
    System.out.println(canReach("01101110", 2, 3)); // false
  }
}
