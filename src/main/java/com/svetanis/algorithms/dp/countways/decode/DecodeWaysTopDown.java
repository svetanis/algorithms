package com.svetanis.algorithms.dp.countways.decode;

// 91. Decode Ways

public final class DecodeWaysTopDown {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int decoding(String s) {
		Integer[] dp = new Integer[s.length() + 1];
		return dfs(s, 0, dp);
	}

	private static int dfs(String s, int index, Integer[] dp) {
		int n = s.length();
		if (index == n) {
			return 1;
		}
		char c = s.charAt(index);
		if (c == '0') {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int ways = dfs(s, index + 1, dp);
		boolean valid = index + 1 < n;
		if (valid && (c == '1' || (c == '2' && s.charAt(index + 1) <= '6'))) {
			ways += dfs(s, index + 2, dp);
		}
		return dp[index] = ways;
	}

	public static void main(String[] args) {
		System.out.println(decoding("12")); // 2 : AB (1 2) or L (12)
		System.out.println(decoding("121")); // 3 : ABA (1 2 1) or AU (1 21) or LA (12 1)
		System.out.println(decoding("226")); // 3 : BZ (2 26), VF (22 6), BBF (2 2 6)
		System.out.println(decoding("06")); // 0
	}
}
