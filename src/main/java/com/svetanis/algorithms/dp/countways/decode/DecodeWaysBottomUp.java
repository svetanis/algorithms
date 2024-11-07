package com.svetanis.algorithms.dp.countways.decode;

// 91. Decode Ways

public final class DecodeWaysBottomUp {
	// Time Complexity: O(n)

	public static int decoding(String s) {
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			if (s.charAt(i - 1) != '0') {
				dp[i] = dp[i - 1];
			}
			if (i > 1 && s.charAt(i - 2) != '0') {
				int val = Integer.parseInt(s.substring(i - 2, i));
				if (val <= 26) {
					dp[i] += dp[i - 2];
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(decoding("12")); // 2 : AB (1 2) or L (12)
		System.out.println(decoding("121")); // 3 : ABA (1 2 1) or AU (1 21) or LA (12 1)
		System.out.println(decoding("226")); // 3 : BZ (2 26), VF (22 6), BBF (2 2 6)
		System.out.println(decoding("06")); // 0
	}
}
